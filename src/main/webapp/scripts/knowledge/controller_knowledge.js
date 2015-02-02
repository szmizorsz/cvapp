'use strict';

cvappApp.controller('KnowledgeController', function ($scope, resolvedKnowledge, Knowledge) {

        $scope.knowledges = resolvedKnowledge;

        $scope.create = function () {
            Knowledge.save($scope.knowledge,
                function () {
                    $scope.knowledges = Knowledge.query();
                    $('#saveKnowledgeModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.knowledge = Knowledge.get({id: id});
            $('#saveKnowledgeModal').modal('show');
        };

        $scope.delete = function (id) {
            Knowledge.delete({id: id},
                function () {
                    $scope.knowledges = Knowledge.query();
                });
        };

        $scope.clear = function () {
            $scope.knowledge = {categoryEn: null, categoryHu: null, detailsEn: null, detailsHu: null, id: null};
        };
    });
