'use strict'

var module = angular.module('emiratestodo.controllers', []);

var getServiceUrl = function() {
    return "http://localhost:8090/";
}

module.controller('MainController', function($scope, $http){
    // when landing on the page, get all todos and show them
    $scope.list = function() {
        $scope.filter = 'ALL';
        $http.get(getServiceUrl() + 'todo/list')
        .success(function(data) {
            if (data) {
                $scope.todos = data;
                $scope.todosOriginal = [];
                $scope.todos.map((e) => {
                    $scope.todosOriginal.push(e);
                });
            } else {
                $scope.todos = [];
            }
        })
        .error(function(data) {
            console.log('Error: ' + data);
        });
    }

    $scope.list();

    $scope.createTask = function(addValue) {
        var taskAlreadyPresent = false;
        $scope.todos.map((e) => {
            if (e.task == addValue) {
                taskAlreadyPresent = true;
            }
        });
        if (!taskAlreadyPresent) {
            var todoToBeAdded = {};
            todoToBeAdded.task = addValue;
            todoToBeAdded.status = 'PENDING';

            var url = getServiceUrl() + 'todo/add-task';
            $http.post(url, todoToBeAdded)
            .success(function(data) {
                if (data) {
                    $scope.list();
                    $scope.addTaskValue = '';
                }
            })
            .error(function(data) {
                console.log('Error: ' + data);
            });
        } else {
            alert ("Task already present");
        }
    }

    $scope.deleteTask = function(todo) {
        if (todo && todo.id) {
            var url = getServiceUrl() + 'todo/delete-task';
            $http.post(url, todo)
            .success(function(data) {
                if (data) {
                    if (data.status == 'SUCCESS') {
                        $scope.list();
                    }
                }
            })
            .error(function(data) {
                console.log('Error: ' + data);
            });
        }
    }

    $scope.toggleTodoStatus = function(todo) {
        if (todo && todo.id) {
            if (todo.status == 'PENDING') {
                todo.status = 'COMPLETED';
            } else {
                todo.status = 'PENDING';
            }
            var url = getServiceUrl() + 'todo/toggle-status';
            $http.put(url, todo)
            .success(function(data) {
                if (data) {
                    if (data.status == 'SUCCESS') {
                        $scope.list();
                    }
                }
            })
            .error(function(data) {
                console.log('Error: ' + data);
            });
        }
    }

    $scope.toggleFilter = function(filterVal) {
        var todos = [];
        if (filterVal == 'ALL') {
            $scope.todosOriginal.map((e) => {
                todos.push(e);
            });
        } else {
            $scope.todosOriginal.map((e) => {
                if (e.status == filterVal) {
                    todos.push(e);
                }
            });
        }
        $scope.todos = todos;
        $scope.filter = filterVal;            
    }

});