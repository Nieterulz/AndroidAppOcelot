"use strict";
const MongoClient = require("mongodb").MongoClient;
let db;
let ObjectId = require("mongodb").ObjectID;
const Asistentes = function () {};

Asistentes.prototype.connectDb = function (callback) {
    MongoClient.connect(
        "mongodb+srv://testPNET:testPNET123@ajs-pnet-2019-2020-0iben.mongodb.net/test?retryWrites=true&w=majority",
        { useNewUrlParser: true, useUnifiedTopology: true },
        function (err, database) {
            if (err) {
                callback(err);
            }
            db = database.db("android").collection("asistentes");
            callback(err, database);
        }
    );
};

Asistentes.prototype.add = function (asistente, callback) {
    return db.insertOne(asistente, callback);
};

Asistentes.prototype.get = function (_id, callback) {
    return db.find({ _id: ObjectId(_id) }).toArray(callback);
};

Asistentes.prototype.getAll = function (callback) {
    return db.find({}).toArray(callback);
};

Asistentes.prototype.update = function (_id, updatedAsistente, callback) {
    delete updatedAsistente._id;
    return db.updateOne(
        { _id: ObjectId(_id) },
        { $set: updatedAsistente },
        callback
    );
};
Asistentes.prototype.remove = function (_id, callback) {
    return db.deleteOne({ _id: ObjectId(_id) }, callback);
};
Asistentes.prototype.removeAll = function (callback) {
    return db.deleteMany({}, callback);
};

module.exports = new Asistentes();
