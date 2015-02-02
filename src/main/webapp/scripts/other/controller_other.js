'use strict';

cvappApp.controller('OtherController', function ($scope, resolvedOther, Other) {

        $scope.others = resolvedOther;

        $scope.create = function () {
            Other.save($scope.other,
                function () {
                    $scope.others = Other.query();
                    $('#saveOtherModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.other = Other.get({id: id});
            $('#saveOtherModal').modal('show');
        };

        $scope.delete = function (id) {
            Other.delete({id: id},
                function () {
                    $scope.others = Other.query();
                });
        };

        $scope.clear = function () {
            $scope.other = {categoryEn: null, categoryHu: null, descriptionEn: null, descriptionHu: null, id: null};
        };
    });
