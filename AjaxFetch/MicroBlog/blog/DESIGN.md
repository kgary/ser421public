# Design

The Pain Reporting Portal consists of four major pieces.

1. [Dashboard](#dashboard)
2. [API](#api)
3. [CLI](#api)
4. [Rules Engine](#rules-engine)

## Dashboard

The dashboard is a website that allows medical researchers to manage clinical trials.
Providing up-to-date information on patient participation.

The dashboard is organized using the [MVC pattern](https://en.wikipedia.org/wiki/Model–view–controller).
It leverages [Sequelize](http://docs.sequelizejs.com/) to power the model, [Hapi](http://hapijs.com/) to provide
 controller services, and [Handlebars](http://handlebarsjs.com/) to render the views.

**Relevant Folders**

``` sh
controller
model
view
```

## API

The API is a [RESTful service](https://en.wikipedia.org/wiki/Representational_state_transfer) that powers the
application that patients use to complete surveys.

The API leverages the same model used by the dashboard, and sends survey information to the companion application in
JSON format. The model is powered by [Sequelize](http://docs.sequelizejs.com/) and the API is handled by
[Hapi](http://hapijs.com/).

**Relevant Folders**

``` sh
api
view
```

## CLI

The CLI is a collection of tools to help administrators and developers manage all the Pain Reporting Portal components.

The CLI leverages [NPM](https://docs.npmjs.com/misc/scripts) for running tasks,
[JSDoc](https://github.com/jsdoc3/jsdoc) for documentation,
[Bower](http://bower.io/) to manage UI libraries,
and [PM2](http://pm2.keymetrics.io/) for production server management.

The CLI also handles testing and code smell detection for the code base. It uses
[AVA](https://github.com/sindresorhus/ava) for testing,
[Proxyquire](https://github.com/thlorenz/proxyquire) for mock dependency injection,
[Sinon](http://sinonjs.org/) for function mocking and stubbing,
[ESLint](http://eslint.org/) for Javascript lint checking,
[JSONlint](https://github.com/zaach/jsonlint) for configuration validation,
[Remark Lint](https://github.com/wooorm/remark-lint) for markdown documentation lint checking,
and [Stylelint](http://stylelint.io/) for Cascading Style Sheet lint checking.

**Relevant Files and Folder**

``` sh
package.json
.eslintrc.yml
.remarkrc
.stylelintrc.yml
task
```

## Rules Engine

The rules engine responds to events, then looks at patient and trial information and determines when surveys should be
offered to a patient.
It implements a small [expert system](https://en.wikipedia.org/wiki/Expert_system) in Javascript.

**Relevant Folder**

``` sh
rule
```
