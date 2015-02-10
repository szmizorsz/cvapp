'use strict';

cvappApp.controller('CompanyController', function ($scope, resolvedCompany, Company, resolvedProject) {

        $scope.companys = resolvedCompany;
        $scope.projects = resolvedProject;

        $scope.create = function () {
            Company.save($scope.company,
                function () {
                    $scope.companys = Company.query();
                    $('#saveCompanyModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.company = Company.get({id: id});
            $('#saveCompanyModal').modal('show');
        };

        $scope.delete = function (id) {
            Company.delete({id: id},
                function () {
                    $scope.companys = Company.query();
                });
        };

        $scope.clear = function () {
            $scope.company = {nameEn: null, nameHu: null, periodEn: null, periodHu: null, descriptionEn: null, descriptionHu: null, id: null};
        };
    });
