'use strict';

cvappApp.controller('ProjectController', function ($scope, resolvedProject, Project, resolvedCompany, resolvedTechnology) {

        $scope.projects = resolvedProject;
        $scope.companys = resolvedCompany;
        $scope.technologys = resolvedTechnology;

        $scope.create = function () {
            Project.save($scope.project,
                function () {
                    $scope.projects = Project.query();
                    $('#saveProjectModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.project = Project.get({id: id});
            $('#saveProjectModal').modal('show');
        };

        $scope.delete = function (id) {
            Project.delete({id: id},
                function () {
                    $scope.projects = Project.query();
                });
        };

        $scope.clear = function () {
            $scope.project = {nameEn: null, nameHu: null, descriptionEn: null, descriptionHu: null, clientEn: null, clientHu: null, start: null, end: null, id: null};
        };
    });
