import React from "react";
// nodejs library that concatenates classes
import classNames from "classnames";
// react plugin used to create charts
import {Line, Bar} from "react-chartjs-2";

// reactstrap components
import {
    Button,
    Card,
    CardBody,
    Row,
    Col,
    CardText, CardFooter,
    Modal,
    Input
} from "reactstrap";

// core components
import {
    chartExample1,
    chartExample2,
    chartExample3,
    chartExample4
} from "variables/charts.js";
import axios from 'axios';

const REST_HOST = process.env.REACT_APP_REST_HOST

class Dashboard extends React.Component {
    constructor(props) {
        super(props);
        let date_now = new Date();
        this.state = {
            bigChartData: "data1",
            modal: false,
            city_data: {
                cityName: '',
                cityId: '',
                cityLatitude: '',
                cityLongitude: '',
            },
            weatherData: {
                category: '',
                color: '',
                pollutants: [],
                date: date_now.toDateString(),
                air_score: 0,
                health_recommendations: ''

            },
            pollutantData: {
                display_name: "",
                full_name: "",
                concentration_value: 0,
                concentration_units: 0,
                color: '',
                source: '',
                effects: ''
            }
        };
    }

    setBgChartData = name => {
        this.setState({
            bigChartData: name
        });
    };

    parsePollutants = (pollutants) => {
        let non_null_pollutants = [];

        Object.keys(pollutants).map((key) => {
            const pollutant = pollutants[key];
            if (pollutant !== null)
                non_null_pollutants.push(pollutant);
        });

        return non_null_pollutants;
    };

    parseDate = (dateStr) => {
        return new Date(dateStr).toLocaleString('pt-PT', {
            'hour12': false,
        })
    };


    get_air_quality_data = async () => {

        const dataD = localStorage.getItem('historical_city_data');
        let rdata = [];
        let data = [];
        if (dataD === null) {
            const city_data = JSON.parse(localStorage.getItem('city_data'));
            await this.setState({
                city_data: city_data
            });

            const latitude = this.state.city_data.cityLatitude;
            const longitude = this.state.city_data.cityLongitude;

            const href = `${REST_HOST}/air/lat=${latitude}&long=${longitude}`;

            rdata = await axios.get(href);
            data = rdata.data;

        }else {
            data = JSON.parse(dataD);
        }



        const current_air_condition = data.current_conditions;
        const pollutants = this.parsePollutants(current_air_condition.pollutants);
        const date = this.parseDate(current_air_condition.date);


        const weatherData = {
            category: current_air_condition.category,
            color: current_air_condition.color,
            pollutants: pollutants,
            date: date,
            air_score: current_air_condition.air_score,
            health_recommendations: current_air_condition.health_recommendations.active
        };

        await this.setState({weatherData: weatherData});

        console.log(this.state);
    };


    componentDidMount() {
        this.get_air_quality_data().then(r => {
        });
    }

    pollutantInfo = async (e) => {
        this.toggleModal();
        let pollutantData = {
            display_name: e.target.innerText,
            full_name: "",
            concentration_value: 0,
            concentration_units: 0,
            color: '',
            source: '',
            effects: ''
        };

        for (let i = 0; i < this.state.weatherData.pollutants.length; i++) {
            const pollutant = this.state.weatherData.pollutants[i];
            if (pollutantData.display_name === pollutant.display_name) {
                pollutantData.full_name = pollutant.full_name;
                pollutantData.concentration_value = pollutant.concentration.value;
                pollutantData.concentration_units = pollutant.concentration.units;
                pollutantData.color = pollutant.aqi_information.baqi.color;
                pollutantData.source = pollutant.sources_and_effects.sources;
                pollutantData.effects = pollutant.sources_and_effects.effects
            }
        }

        await this.setState({pollutantData: pollutantData});

    };

    toggleModal = () => {
        this.setState({
            modal: !this.state.modal
        });
    };


    render() {
        return (
            <>
                <div className="content">
                    <Row>
                        <Col md="12">
                            <Card className="card-user">
                                <CardBody>
                                    <CardText/>
                                    <div className="author">
                                        <div className="block block-one"/>
                                        <div className="block block-two"/>
                                        <div className="block block-three"/>
                                        <div className="block block-four"/>
                                        <a onClick={e => e.preventDefault()}>
                                            <img
                                                alt="..."
                                                className="avatar"
                                                src={require("assets/img/qualidade-do-ar.png")}
                                                style={{border: "none", "border-radius": "0"}}
                                            />
                                            <h5 className="title">{this.state.city_data.cityName}</h5>
                                        </a>
                                        <p className="description"
                                           style={{color: this.state.weatherData.color}}>
                                            <b>{this.state.weatherData.category} : {this.state.weatherData.air_score}</b>
                                        </p>
                                        <p className="date">
                                            <b>Last update :</b> &nbsp;<h3>{this.state.weatherData.date}</h3></p>
                                    </div>


                                    <div className="author">
                                        <h1><b>Pollutants</b></h1>
                                    </div>
                                    <div className="button-container">
                                        {
                                            this.state.weatherData.pollutants.map((pollutant) => {
                                                return (
                                                    <Button className="btn-icon btn-round" color="facebook"
                                                            id={pollutant.display_name} onClick={this.pollutantInfo}>
                                                        <span
                                                            style={{
                                                                "font-size": "10px",
                                                                color: pollutant.aqi_information.baqi.color
                                                            }}> {pollutant.display_name}</span>
                                                    </Button>
                                                )
                                            })
                                        }
                                    </div>

                                    <div className="author">
                                        <h1><b>Health Recommendations</b></h1>
                                    </div>
                                    <div className="card-description" style={{"text-align": "center"}}>
                                        {this.state.weatherData.health_recommendations}
                                    </div>
                                </CardBody>
                            </Card>
                        </Col>
                    </Row>
                </div>
                <Modal
                    modalClassName="modal-search"
                    isOpen={this.state.modal}
                    toggle={this.toggleModal}>

                    <Card className="card-user" style={{"margin-bottom": "0px"}}>
                        <CardBody>

                            <div className="author">
                                <div className="block block-one"/>
                                <div className="block block-two"/>
                                <div className="block block-three"/>
                                <div className="block block-four"/>
                                <Button className="btn-icon btn-round" color="facebook"
                                        id={this.state.pollutantData.display_name} onClick={this.pollutantInfo}
                                        style={{width: "100px", height: "100px", "border-radius": "50%"}}>
                                                        <span
                                                            style={{
                                                                "font-size": "20px",
                                                                color: this.state.pollutantData.color
                                                            }}> {this.state.pollutantData.display_name}</span>
                                </Button>
                                <p className="description">
                                    {this.state.pollutantData.full_name}</p>
                                <p className="description"
                                   style={{color: this.state.pollutantData.color}}>
                                    <b>{this.state.pollutantData.concentration_value} {this.state.pollutantData.concentration_units}</b>
                                </p>

                            </div>
                            <div className="author">
                                <b>Source</b>
                            </div>
                            <div className="card-description" style={{"text-align": "center"}}>
                                {this.state.pollutantData.source}
                            </div>
                            <div className="author">
                                <b>Effects</b>
                            </div>
                            <div className="card-description" style={{"text-align": "center"}}>
                                {this.state.pollutantData.effects}
                            </div>
                            <button
                                aria-label="Close"
                                className="close"
                                data-dismiss="modal"
                                type="button"
                                onClick={this.toggleModal}
                            >
                                <i className="tim-icons icon-simple-remove"/>
                            </button>
                        </CardBody>
                    </Card>
                </Modal>
            </>
        );
    }
}

export default Dashboard;
