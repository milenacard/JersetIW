var miAplicacion = angular.module('miAplicacion', []);

miAplicacion.controller('Controlador', [ '$scope', function($scope) {
	$scope.texto = '';
	
	$scope.lista = [ {
		texto : 'Prueba 1',
		seleccionado : true
	}, {
		texto : 'Prueba 2',
		seleccionado : false
	} ];

	$scope.agregar = function() {
		if (($scope.texto == '') ||($scope.texto == null)){
			alert("Digite el Texto");
			return;
		}
		$scope.lista.push({
			texto : $scope.texto,
			seleccionado : false
		});
		$scope.texto = '';
	};

	$scope.eliminar = function() {
		var lista = $scope.lista;
		$scope.lista = [];
		angular.forEach(lista, function(item) {
			if (!item.seleccionado)
				$scope.lista.push(item);
		});
	};
} ]);