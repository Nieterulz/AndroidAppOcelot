"use strict";
const express = require("express");
const router = express.Router();
const asistentesService = require("./asistentes-service");

// Obtiene todos los asistentes
router.get("/", function (req, res) {
    asistentesService.getAll((err, asistentes) => {
        if (err) {
            res.status(500).send({
                msg: err,
            });
        } else if (asistentes === null) {
            res.status(500).send({
                msg: "asistentes null",
            });
        } else {
            res.status(200).send(asistentes);
        }
    });
});

// Añade un nuevo asistente
router.post("/", function (req, res) {
    let asistente = req.body;
    asistentesService.add(asistente, (err, asistente) => {
        if (err) {
            res.status(500).send({
                msg: err,
            });
        } else if (asistente !== null) {
            res.send({
                msg: "Asistente creado",
            });
        }
    });
});

// Elimina todos los asistentes
router.delete("/", function (req, res) {
    asistentesService.removeAll((err) => {
        if (err) {
            res.status(500).send({
                msg: err,
            });
        } else {
            res.status(200).send({
                msg: "Asistentes eliminados",
            });
        }
    });
});

// Obtiene un único asistente
router.get("/:_id", function (req, res) {
    let _id = req.params._id;
    asistentesService.get(_id, (err, asistente) => {
        if (err) {
            res.status(500).send({
                msg: err,
            });
        } else if (asistente === null) {
            res.status(500).send({
                msg: "asistentes null",
            });
        } else {
            res.status(200).send(asistente);
        }
    });
});

// Actualiza la información de un asistente
router.put("/:_id", function (req, res) {
    const _id = req.params._id;
    const updatedAsistente = req.body;
    asistentesService.update(_id, updatedAsistente, (err, numUpdates) => {
        if (err || numUpdates === 0) {
            res.status(500).send({
                msg: err,
            });
        } else {
            res.status(200).send({
                msg: "Asistente actualizado!",
            });
        }
    });
});

// Elimina un asistente
router.delete("/:_id", function (req, res) {
    let _id = req.params._id;
    asistentesService.remove(_id, (err) => {
        if (err) {
            res.status(404).send({
                msg: err,
            });
        } else {
            res.status(200).send({
                msg: "Asistente eliminado",
            });
        }
    });
});

module.exports = router;
