var mod = angular.module('Encuesta', []);

mod.controller('preguntasEncuesta', ['$scope', function($scope) {
	
	$scope.preguntas=[{id:1,
		texto: 'Pregunta 1',
		respuestaValida: 1,
		respuesta: null,
		estado: '',
		respuestas:[{id:1,
	                texto: 'opcion 1'},
	                {id:2,
	                texto: 'opcion 2'},
	                {id:3,
	                texto: 'opcion 3'}]	
	
	}, {id: 2,
		texto: 'Pregunta 2',
		respuestaValida: 1,
		respuesta:null,
		estado: '',
		respuestas:[{id:2,
            texto: 'opcion 1'},
            {id:2,
            texto: 'opcion 2'},
            {id:3,
            texto: 'opcion 3'}]		
	}
	];
	$scope.respuestaValidar = 0;
	
	//Valida que la respuesta ingresada sea valida
	$scope.validar = function(pregunta){
		if (pregunta.respuesta == pregunta.respuestaValida) {
			$scope.respuestasValidar++; //mirar bien
			pregunta.estado = 'ok';
		}else{
			if (pregunta.estado=='ok'&& $scope.respuestaValidar>0) {
				$scope.respuestaValidar--;
			}
			pregunta.estado='error';
		}
	}
	
}]);
	
	
