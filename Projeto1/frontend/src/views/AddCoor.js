import React from "react";

// reactstrap components
import {
    Button,
    Card,
    CardHeader,
    CardBody,
    CardFooter,
    CardText,
    FormGroup,
    Form,
    Input,
    Row,
    Col
} from "reactstrap";
import NotificationAlert from "react-notification-alert";

class AddCoor extends React.Component {
    constructor(props) {
        super(props);
        this.state = {};

        //this.submitCoordinates = this.submitCoordinates.bind(this);
        this.coord = this.coord.bind(this);
        this.latitudeRef = React.createRef();
        this.longitudeRef = React.createRef();
    }


    componentDidMount() {

    }

    coord = async e => {

        e.preventDefault();

        const latitude = this.latitudeRef.current._reactInternalFiber.child.stateNode.valueAsNumber;
        const longitude = this.longitudeRef.current._reactInternalFiber.child.stateNode.valueAsNumber;

        if (isNaN(latitude) || isNaN(longitude)) {
            this.renderErrorNotification(["Os valores de latitude e longitude devem ser números"]);
            return;
        }

        if (latitude > 90 || latitude < -90) {
            this.renderErrorNotification(["Os valores de latitude devem estar compreendidos entre -90 e 90"]);
            return;
        }

        if (longitude > 180 || longitude < -180) {
            this.renderErrorNotification(["Os valores de latitude devem estar compreendidos entre -180 e 180"]);
            return;
        }

        localStorage.setItem("city_data",JSON.stringify({
            cityName: 'Undefined',
            cityId: '',
            cityLatitude: latitude +  '',
            cityLongitude: longitude + '',
        }));


        window.location.href = "/admin/air";
    };

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

    render() {
        return (
            <>
                <div className="react-notification-alert-container">
                    <NotificationAlert ref="notificationAlert"/>
                </div>
                <div className="content">

                    <Row>
                        <Col md="12">
                            <Card>
                                <Form>
                                    <CardHeader>
                                        <h5 className="title">Inserir coordenadas
                                        </h5>
                                    </CardHeader>
                                    <CardBody>

                                        <Row>
                                            <Col className="pr-md-1" md="6">

                                                <label>Latitude</label>
                                                <Input
                                                    id="latitude"
                                                    defaultValue="39.91177"
                                                    placeholder="Latitude"
                                                    type="number"
                                                    step="0.000001"
                                                    ref={this.latitudeRef}

                                                />

                                            </Col>
                                            <Col className="pl-md-1" md="6">

                                                <label>Longitude</label>
                                                <Input
                                                    id="longitude"
                                                    defaultValue="-8.43562"
                                                    placeholder="Longitude..."
                                                    type="number"
                                                    step="0.000001"
                                                    ref={this.longitudeRef}
                                                />

                                            </Col>
                                        </Row>


                                    </CardBody>
                                    <CardFooter>
                                        <Button className="btn-fill" color="primary" type="submit" onClick={this.coord} id="submeter">
                                            Submeter
                                        </Button>
                                    </CardFooter>
                                </Form>
                            </Card>
                        </Col>
                    </Row>

                </div>
            </>
        );
    }
}

export default AddCoor;
