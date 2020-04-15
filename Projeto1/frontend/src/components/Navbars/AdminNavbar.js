import React from "react";

// nodejs library that concatenates classes
import classNames from "classnames";
import debounce from 'lodash/debounce';
import axios from 'axios';
import NotificationAlert from "react-notification-alert";
// reactstrap components
import {
    Button,
    Collapse,
    DropdownToggle,
    DropdownMenu,
    DropdownItem,
    UncontrolledDropdown,
    Input,
    InputGroup,
    NavbarBrand,
    Navbar,
    NavLink,
    Nav,
    Container,
    Modal
} from "reactstrap";

const REST_HOST = process.env.REACT_APP_REST_HOST


class AdminNavbar extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            collapseOpen: false,
            modalSearch: false,
            color: "navbar-transparent",
            city: '',
            search_list: []
        };
    }

    renderErrorNotification(errors, time) {
        if (time === undefined)
            time = 5;
        var options = {};

        options = {
            place: "br",
            message: (
                <div>
                    {errors.map((value) => {
                        return (
                            <li>{value}</li>
                        )
                    })}
                </div>
            ),
            icon: "tim-icons icon-alert-circle-exc",
            type: "danger",
            autoDismiss: time
        };


        this.refs.notificationAlert.notificationAlert(options);

    }

    componentDidMount() {
        window.addEventListener("resize", this.updateColor);
    }

    componentWillUnmount() {
        window.removeEventListener("resize", this.updateColor);
    }

    // function that adds color white/transparent to the navbar on resize (this is for the collapse)
    updateColor = () => {
        if (window.innerWidth < 993 && this.state.collapseOpen) {
            this.setState({
                color: "bg-white"
            });
        } else {
            this.setState({
                color: "navbar-transparent"
            });
        }
    };
    // this function opens and closes the collapse on small devices
    toggleCollapse = () => {
        if (this.state.collapseOpen) {
            this.setState({
                color: "navbar-transparent"
            });
        } else {
            this.setState({
                color: "bg-white"
            });
        }
        this.setState({
            collapseOpen: !this.state.collapseOpen
        });
    };
    // this function is to open the Search modal
    toggleModalSearch = () => {
        this.setState({
            modalSearch: !this.state.modalSearch,
            search_list: []
        });
    };


    getCities = async (e) => {
        if (this.state.city.length < 3) {
            this.renderErrorNotification(["Nome da cidade deve conter pelo menos 3 caracteres"]);
            return;
        }
        const {data} = await axios.get(`${REST_HOST}/cities/${this.state.city.toLowerCase()}`);
        if (data.length > 0 && data !== undefined) {

            const results = data.map(result => ({
                cityName: result.full_name,
                cityId: result.id,
                cityLatitude: result.latitude,
                cityLongitude: result.longitude

            }));

            this.setState({
                search_list: results
            });
        }
    };

    debounceSearch = debounce(this.getCities, 1000);

    toggleSearch = (e) => {
        this.setState({city: e.target.value, search_list: []});
        this.debounceSearch();
    };

    getCity = (e) => {
        const id = e.target.id;

        const parts = id.split(',');
        const latitude = parseFloat(parts[0]);
        const longitude = parseFloat(parts[1]);

        let city_name, city_id;

        for (let i = 0; i < this.state.search_list.length; i++) {
            let city = this.state.search_list[i];
            if (city.cityLatitude === latitude && city.cityLongitude === longitude) {
                city_name = city.cityName;
                city_id = city.cityId;
                break;
            }
        }


        const city_data = {
            cityName: city_name,
            cityId: city_id,
            cityLatitude: latitude,
            cityLongitude: longitude
        }

        localStorage.setItem('city_data', JSON.stringify(city_data));
        window.location.href="/admin/air";
    };


    render() {
        return (
            <>
                <div className="react-notification-alert-container">
                    <NotificationAlert ref="notificationAlert" />
                </div>
                <Navbar
                    className={classNames("navbar-absolute", this.state.color)}
                    expand="lg"
                >
                    <Container fluid>
                        <div className="navbar-wrapper">
                            <div
                                className={classNames("navbar-toggle d-inline", {
                                    toggled: this.props.sidebarOpened
                                })}
                            >
                                <button
                                    className="navbar-toggler"
                                    type="button"
                                    onClick={this.props.toggleSidebar}
                                >
                                    <span className="navbar-toggler-bar bar1"/>
                                    <span className="navbar-toggler-bar bar2"/>
                                    <span className="navbar-toggler-bar bar3"/>
                                </button>
                            </div>
                            <NavbarBrand href="#pablo" onClick={e => e.preventDefault()}>
                                {this.props.brandText}
                            </NavbarBrand>
                        </div>
                        <button
                            aria-expanded={false}
                            aria-label="Toggle navigation"
                            className="navbar-toggler"
                            data-target="#navigation"
                            data-toggle="collapse"
                            id="navigation"
                            type="button"
                            onClick={this.toggleCollapse}
                        >
                            <span className="navbar-toggler-bar navbar-kebab"/>
                            <span className="navbar-toggler-bar navbar-kebab"/>
                            <span className="navbar-toggler-bar navbar-kebab"/>
                        </button>
                        <Collapse navbar isOpen={this.state.collapseOpen}>
                            <Nav className="ml-auto" navbar>
                                <InputGroup className="search-bar">
                                    <Button
                                        color="link"
                                        data-target="#searchModal"
                                        data-toggle="modal"
                                        id="search-button"
                                        onClick={this.toggleModalSearch}
                                    >
                                        <i className="tim-icons icon-zoom-split"/>
                                        <span className="d-lg-none d-md-block">Search</span>
                                    </Button>
                                </InputGroup>

                                <li className="separator d-lg-none"/>
                            </Nav>
                        </Collapse>
                    </Container>
                </Navbar>

                <Modal
                    modalClassName="modal-search"
                    isOpen={this.state.modalSearch}
                    toggle={this.toggleModalSearch}

                >
                    <div className="modal-header">
                        <Input id="inlineFormInputGroup" placeholder="SEARCH" type="text" onChange={this.toggleSearch}/>
                        <button
                            aria-label="Close"
                            className="close"
                            data-dismiss="modal"
                            type="button"
                            onClick={this.toggleModalSearch}
                        >
                            <i className="tim-icons icon-simple-remove"/>
                        </button>
                    </div>
                    {this.state.search_list.length > 0 &&
                    <UncontrolledDropdown>
                        <DropdownToggle
                            color="default"
                            data-toggle="dropdown"
                            nav>
                            {this.state.search_list.map((city, index) => {
                                const city_id = `${city.cityLatitude},${city.cityLongitude}`;
                                return (
                                    <>
                                        <NavLink tag="li">
                                            <DropdownItem
                                                id={city_id} className="nav-item"
                                                onClick={this.getCity}>{city.cityName}</DropdownItem>
                                        </NavLink>
                                        <DropdownItem divider tag="li"/>
                                    </>
                                )
                            })}

                        </DropdownToggle>

                    </UncontrolledDropdown>
                    }
                    < /Modal>
                    </>

                    );
                    }
                    }

                    export default AdminNavbar;
