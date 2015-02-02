'use strict';

cvappApp.controller('LanguagesController', function ($scope, resolvedLanguages, Languages) {

        $scope.languagess = resolvedLanguages;

        $scope.create = function () {
            Languages.save($scope.languages,
                function () {
                    $scope.languagess = Languages.query();
                    $('#saveLanguagesModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.languages = Languages.get({id: id});
            $('#saveLanguagesModal').modal('show');
        };

        $scope.delete = function (id) {
            Languages.delete({id: id},
                function () {
                    $scope.languagess = Languages.query();
                });
        };

        $scope.clear = function () {
            $scope.languages = {nameEn: null, nameHu: null, levelEn: null, levelHu: null, id: null};
        };
    });
