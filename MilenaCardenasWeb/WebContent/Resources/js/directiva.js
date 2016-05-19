//debe decir con que modulos voy a trabajar en []
angular.module('miAplicacion', []).directive( 'persona',function() {
			return { //un objeto Json con el que angular lo configura
				restrict : 'E', //variable que puede tomar varios valores, E->significa etiqueta, A->atributo, C->clase, M ->comentario
				scope : {},  //defino el scope como vacio
				replace : true, //reemplaza la etiqueta por el contenido que hay en persona
				template : '<h1> {{nombre_completo}} </h1>', //renderizar
				link : function(scope, elemento, atributos) { //con que voy a unir esa etiqueta
					scope.nombre_completo = atributos.nombre + ' ' + atributos.apellido;
					elemento.bind('click', function() { //aparezca una alerta en el elemento cuando se haga clic
						alert('Hola, soy ' + scope.nombre_completo);
					});
				}
			};
		});
