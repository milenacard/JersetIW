var app = angular.module('Cliente', ['ngRoute', 'ngCookies']);
var servicioLogin = 'http://localhost:8080/MilenaCardenasWeb/rest/usuario';

app.controller('contLogin', function($scope, usuario, $location,$cookies){
	
	$scope.nombreUsuario ='';
	$scope.pws ='';
	
	$scope validar = function(){
		
		usuario.validar($scope.nombreUsuario,
				$scope.pw).success(function(data){
					if(data!=''){
						alert(data);
					}else{
						$location.url('/');
					}
		});
			
		//al controlador debo inyectarle el servicio
	}
});

//defini un servicio llamado usuario
app.service('Usuario', function($http){
	
	this.validar = function(usuario, contra){
		return $http({ //llamado al servicio web
			method: 'GET', //metodo con el que se consume el SW
			url: servicioLogin, //URL
			param: { //parametros de entrada para el SW
				login: usuario,
				clave: contra
			}
			
		});
		
	};
	
	
});