var sqlite = require('sqlite3').verbose();
var express = require('express');
var app = express();

var db = new sqlite.Database('ratemaplate.sqlite3');

app.get('/create_user', function (req, res) {
    let insert_user = 'INSERT INTO User VALUES (?, ?, ?);';

    //TODO: Change to req.body
    let email = req.query.email;
    let username = req.query.username;
    let password = req.query.password;

    // if the form is empty
    if (email == null) {
        res.send('{response: "No email supplied", error: true}');
        return;
    }

    if (username == null) {
        res.send('{response: "No username supplied", error: true}');
        return;
    }

    if (password == null) {
        res.send('{response: "No password supplied", error: true}');
        return;
    }

    //
    console.log(email);
    console.log(username);
    console.log(password);

    if (check_email(email)) {
        res.send('{response: "This email is already in use", error: true}');
        return;
    }

    if (check_username(username)) {
        res.send('{response: "This username is already in use", error: true}');
        return;
    }

    db.run(insert_user, [email, username, password], function(err) {
        if (err) throw err;
    });

    res.send('{response: "User created successfully", error: false}');
});

app.get('/login_user', function(req, res) {
    let sql = "SELECT COUNT(*) AS count FROM User WHERE email=? AND password=?;";

    let email = req.query.email;
    let password = req.query.password;

    db.all(sql, [email, password], function(err, rows) {
        if (err) throw err;

        rows.forEach(function(row) {
            if (row.count > 0) {
                res.send('{response: "Logged in", error: false}');
            } else {
                res.send('{response: "Invalid email or password", error: true}');
            }
        });
    })
});

function check_email(email) {
    let sql = 'SELECT COUNT(email) AS count FROM User WHERE email=?';
    var error;
    db.get(sql, [email], function(err, row) {
        if (err) throw err;

        error = row.count != 0;
    });
    return error;
}

function check_username(username) {
    let sql = 'SELECT COUNT(username) AS count FROM User WHERE username=?';
    var error;
    db.get(sql, [username], function(err, row) {
        if (err) throw err;
        error = row.count != 0;
    });
    return error;
}

app.listen(8080);