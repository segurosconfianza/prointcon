FrmMainApp.directive('customLoad', function($compile) {

	  return {
	    restrict: 'E',
	    template: '<div class="modal fade" id="{{nameModal}}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">'+
				  '  <div class="modal-dialog">'+
				  '  <div class="modal-content">'+
				  '    <div class="modal-header-alert">'+				  
				  '      <h4 class="modal-title" id="myModalLabel"><font color="white" ><b>CARGANDO DATOS&nbsp;&nbsp;</b></font><span class="badge"><span class="glyphicon glyphicon-exclamation-sign"> </span></span></h4>'+
				  '    </div>'+
				  '    <div class="modal-body">'+
				  '      <button class="btn btn-lg btn-warning"><span class="glyphicon glyphicon-refresh glyphicon-refresh-animate"></span> Cargando...</button>'+
				  '    </div>'+
				  '    <div class="modal-footer">'+				  
				  '    </div>'+
				  '  </div>'+
				  '  </div>'+
				  '</div>',
	    scope : { nameModal: '@', labelError : '@' }, 
	    replace : false,
	    transclude : false,
	    controller : controller
	  };

	  function controller($scope, $attrs) {		  		
    	//metodo externo para mostrar la alerta
    	$scope.$on('loadDataLoadOn', function( event) { 
    		$('#'+$scope.nameModal).modal('show');
 	    });
    	
    	$scope.$on('loadDataLoadOff', function( event) { 
    		$('#'+$scope.nameModal).modal('hide');
 	    });
	  }
    	    	  	 
});