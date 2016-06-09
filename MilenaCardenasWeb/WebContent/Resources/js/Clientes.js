var app = angular.module('Clientes', []);
var servicioLista = 'http://localhost:8080/MilenaCardenasWeb/rest/cliente';

app.controller('listaClientes', function($scope, Clientes){
	Clientes.listaClientes().success(function(data){		
		$scope.clientes = data.clienteDTOWs; //como esta en el web/DTO
		//alert($scope.clientes); Para mostrar lo que tiene la variable
	});	
});

app.service('Clientes', function($http){
	//http pide una estructura  de datos
	this.listaClientes = function(){
		return $http({
			method: 'GET',
			url: servicioLista
			});
	}
});