const express = require("express");
const app = express();
const logger = require("morgan");
const http = require("http");
const path = require("path");
const PORT = process.env.PORT || 8080;
const bodyParser = require("body-parser");
const baseAPI = "/api/v1";

const asistentesService = require("./routes/asistentes-service");
const asistentes = require("./routes/asistentes");
const cors = require("cors");

app.use(cors());
app.use(bodyParser.json());
app.use(logger("dev"));
app.use(
    bodyParser.urlencoded({
        extended: true,
    })
);

app.use("/asistentes", asistentes);
const server = http.createServer(app);

asistentesService.connectDb(function (err) {
    if (err) {
        console.log("Could not connect with MongoDB – asistentesService");
        process.exit(1);
    }
    server.listen(PORT, function () {
        console.log("Server up and running on localhost:" + PORT);
    });
});
