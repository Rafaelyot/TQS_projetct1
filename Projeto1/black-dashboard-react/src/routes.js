
import AirQuality from "views/AirQualiy.js";
import Historical from "views/Historical.js";
import Icons from "views/Icons.js";
var routes = [
  {
    path: "/air",
    name: "Current Air Quality",
    icon: "tim-icons icon-alert-circle-exc",
    component: AirQuality,
    layout: "/admin"
  },
  {
    path: "/historical",
    name: "Historical",
    icon: "tim-icons icon-chart-bar-32",
    component: Historical,
    layout: "/admin"
  },
  {
    path: "/icons",
    name: "Air Quality",
    icon: "tim-icons icon-chart-pie-36",
    component: Icons,
    layout: "/admin"
  }


];
export default routes;
