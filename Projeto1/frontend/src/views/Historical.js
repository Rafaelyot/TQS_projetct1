import React from "react";
// nodejs library that concatenates classes
import classNames from "classnames";
// react plugin used to create charts
import {Line, Bar} from "react-chartjs-2";

// reactstrap components
import {
    Button,
    ButtonGroup,
    Card,
    CardHeader,
    CardBody,
    CardTitle,
    DropdownToggle,
    DropdownMenu,
    DropdownItem,
    UncontrolledDropdown,
    Label,
    FormGroup,
    Input,
    Table,
    Row,
    Col,
    UncontrolledTooltip,
    Form,
} from "reactstrap";

// core components
import {
    chartExample1,
    chartExample2,
    chartExample3,
    chartExample4
} from "variables/charts.js";
import axios from "axios";
import NotificationAlert from "react-notification-alert";

const REST_HOST = process.env.REACT_APP_REST_HOST

class Dashboard extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            bigChartData: "data1",
            labels: [],
            dataset_data: [],
            raw_dates: [],
            horas: 3,
            city_data: {
                cityName: 'Ansiao',
                cityId: '',
                cityLatitude: '39.91177',
                cityLongitude: '-8.43562',

            }

        };

        this.horasInput = React.createRef();
    }

    componentDidMount() {

        this.loadData();
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


    loadData = async () => {
        const city_data = JSON.parse(localStorage.getItem('city_data'));

        if(city_data !== null) {
            await this.setState({
                city_data: city_data
            });
        }

        const latitude = this.state.city_data.cityLatitude;
        const longitude = this.state.city_data.cityLongitude;
        let hours = this.state.horas;
        console.log(latitude);
        console.log(longitude);
        console.log(hours);
        const href = `${REST_HOST}/air/history/lat=${latitude}&long=${longitude}&hours=${hours}`;
        let data = []
        try {
            const dataR = await axios.get(href);
            data = dataR.data;
        } catch (e) {
            this.renderErrorNotification(["Erro a fazer o pedido"]);
            return;
        }

        const historical = data.temporal_conditions;
        const historical_data = [];
        historical.map((value) => {
            historical_data.push({
                date: new Date(value.date).toLocaleString('pt-PT', {
                    'hour12': false,
                }),
                air_score: value.air_score,
                raw_date: Date.parse(value.date)
            })
        });

        const labels = [];
        const dataset_data = [];
        const raw_dates = [];
        historical_data.map((value) => {
            labels.push(value.date);
            dataset_data.push(value.air_score);
            raw_dates.push(value.raw_date);
        });


        await this.setState({
                labels: labels,
                dataset_data: dataset_data,
                raw_dates: raw_dates
            }
        );

    };

    fullData = canvas => {
        let ctx = canvas.getContext("2d");

        let gradientStrokeForecast = ctx.createLinearGradient(0, 230, 0, 50);
        let gradientStrokeHistorical = ctx.createLinearGradient(0, 230, 0, 50);

        gradientStrokeForecast.addColorStop(1, "rgba(29,140,248,0.2)");
        gradientStrokeForecast.addColorStop(0.4, "rgba(29,140,248,0.0)");
        gradientStrokeForecast.addColorStop(0, "rgba(29,140,248,0)");


        gradientStrokeHistorical.addColorStop(1, "rgb(186, 84, 245,0.2)");
        gradientStrokeHistorical.addColorStop(0.4, "rgba(29,140,248,0.0)");
        gradientStrokeForecast.addColorStop(0, "#ba54f5");


        const dataSize = this.state.dataset_data.length;

        const historical_data = this.state.dataset_data.slice(0, dataSize / 2);
        const forecast_data = this.state.dataset_data.slice(dataSize / 2, dataSize + 1);
        for (let i = 0; i < dataSize / 2; i++) {
            historical_data.push(null);
            forecast_data.unshift(null);
        }


        return {
            labels: this.state.labels,
            datasets: [
                {
                    label: "Historical Score",
                    fill: true,
                    backgroundColor: gradientStrokeHistorical,
                    borderColor: "#ba54f5",
                    borderWidth: 2,
                    borderDash: [],
                    borderDashOffset: 0.0,
                    pointBackgroundColor: "#ba54f5",
                    pointBorderColor: "rgba(255,255,255,0)",
                    pointHoverBackgroundColor: "#ba54f5",
                    pointBorderWidth: 20,
                    pointHoverRadius: 4,
                    pointHoverBorderWidth: 15,
                    pointRadius: 4,
                    data: historical_data
                },
                {
                    label: "Forecast Score",
                    fill: true,
                    backgroundColor: gradientStrokeForecast,
                    borderColor: "#1f8ef1",
                    borderWidth: 2,
                    borderDash: [],
                    borderDashOffset: 0.0,
                    pointBackgroundColor: "#1f8ef1",
                    pointBorderColor: "rgba(255,255,255,0)",
                    pointHoverBackgroundColor: "#1f8ef1",
                    pointBorderWidth: 20,
                    pointHoverRadius: 4,
                    pointHoverBorderWidth: 15,
                    pointRadius: 4,
                    data: forecast_data
                }
            ]
        };
    };


    elementClick = (e) => {
        if (e.length === 0)
            return;
        const data = e[0];
        const index = data._index;

        const pickedRawData = this.state.raw_dates[index];

        this.fetchTemporalAir(pickedRawData)

    };

    fetchTemporalAir = async (raw_data) => {

        const latitude = this.state.city_data.cityLatitude;
        const longitude = this.state.city_data.cityLongitude;

        const href = `${REST_HOST}/air/by-date/lat=${latitude}&long=${longitude}&datetime=${raw_data}`;
        let data = [];
        try {
            const Rdata = await axios.get(href);
            data = Rdata.data;
        } catch (e) {
            this.renderErrorNotification(["Erro a fazer o pedido"]);
            return;
        }


        localStorage.setItem('historical_city_data', JSON.stringify(data));
        window.location.href = "/admin/air"

    }


    defineHours = async (event) => {
        event.preventDefault();
        const horas = this.horasInput.current._reactInternalFiber.child.stateNode.valueAsNumber;

        if(horas < 0 || horas > 20){
            this.renderErrorNotification(["Valor de horas deve estar entre 1 e 20"]);
            return;
        };

        await this.setState({
            horas: horas
        });
        this.loadData();
    };

    render() {
        return (
            <>
                <div className="react-notification-alert-container">
                    <NotificationAlert ref="notificationAlert"/>
                </div>
                <div className="content">
                    <Row>
                        <Col xs="12">
                            <Card className="card-chart">
                                <CardHeader>
                                    <Form onSubmit={this.defineHours}>
                                        <Row>

                                            <Col className="text-left" sm="6">
                                                <h5 className="card-category">{this.state.city_data.cityName}</h5>
                                                <CardTitle tag="h2">Historical/Forecast</CardTitle>
                                            </Col>
                                            <Col sm="3">
                                                <label>Horas</label>
                                                <Input
                                                    ref={this.horasInput}
                                                    defaultValue={this.state.horas}
                                                    placeholder="Horas"
                                                    type="number"
                                                    step="1"
                                                    name="horas"
                                                    id="horas"

                                                />
                                            </Col>
                                            <Col sm="3">
                                                <Button className="btn-fill" color="primary" type="submit">
                                                    Submeter
                                                </Button>

                                            </Col>

                                        </Row>
                                    </Form>
                                </CardHeader>
                                <CardBody>
                                    <div className="chart-area">
                                        <Line
                                            data={this.fullData}
                                            options={chartExample1.options}
                                            onElementsClick={this.elementClick}
                                        />
                                    </div>
                                </CardBody>
                            </Card>
                        </Col>
                    </Row>
                </div>

            </>
        );
    }
}

export default Dashboard;
