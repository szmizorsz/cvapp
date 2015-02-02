'use strict';

cvappApp.controller('EducationController', function ($scope, resolvedEducation, Education) {

        $scope.educations = resolvedEducation;

        $scope.create = function () {
            Education.save($scope.education,
                function () {
                    $scope.educations = Education.query();
                    $('#saveEducationModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.education = Education.get({id: id});
            $('#saveEducationModal').modal('show');
        };

        $scope.delete = function (id) {
            Education.delete({id: id},
                function () {
                    $scope.educations = Education.query();
                });
        };

        $scope.clear = function () {
            $scope.education = {periodEn: null, periodHu: null, descriptionEn: null, descriptionHu: null, id: null};
        };
    });
