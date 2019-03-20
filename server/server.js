var sqlite = require('sqlite3').verbose();
var express = require('express');
var app = express();

var db = new sqlite.Database('ratemaplate.sqlite3');

app.get('/create_user', function (req, res) {
    let insert_user = 'INSERT INTO User VALUES (?, ?, ?, "user");';

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

    db.get('SELECT COUNT(email) AS count FROM User WHERE email=?', [email], function(err, row) {
        if (err) throw err;

        if (row.count != 0) {
            res.send('{response: "This email is already in use", error: true}');
        } else {
            db.get('SELECT COUNT(username) AS count FROM User WHERE username=?', [username], function(err, row) {
                if (err) throw err;

                if (row.count != 0) {
                    res.send('{response: "This username is already in use", error: true}');
                } else {
                    db.run(insert_user, [email, username, password], function(err) {
                        if (err) throw err;
                    });

                    res.send('{response: "User created successfully", error: false}');
                }
            });
        }
    });
});

app.get('/login_user', function(req, res) {
    let sql = "SELECT COUNT(*) AS count, account_type FROM User WHERE email=? AND password=?;";

    let email = req.query.email;
    let password = req.query.password;

    db.all(sql, [email, password], function(err, rows) {
        if (err) throw err;

        rows.forEach(function(row) {
            console.log(row);
            if (row.count > 0) {
                res.send('{response: "Logged in", error: false, account_type: ' + row.account_type + '}');
            } else {
                res.send('{response: "Invalid email or password", error: true}');
            }
        });
    })
});

app.listen(8080);