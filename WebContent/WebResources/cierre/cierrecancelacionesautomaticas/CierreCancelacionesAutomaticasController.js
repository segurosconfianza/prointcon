var FrmMainApp=angular.module('FrmMainApp');

FrmMainApp.controller('CierreCancelacionesAutomaticasController', ['$scope', 'CierreCancelacionesAutomaticasService', '$filter', function($scope, Service, $filter) { 
	
	$scope.Params = {};
	$scope.Result = false;
	$scope.Boton = true;	
	$scope.BotonLoader = false;
	$scope.Error = false;
	$scope.options={};	
	$scope.paramsSend={};	
	
	$scope.init = function() {
		Service.loadData().then(function(dataResponse) {  
			if(dataResponse.data.error!=undefined)
				$scope.sendAlert(dataResponse.data.tituloError+': '+dataResponse.data.error);
	    	else{
	    		$scope.title=dataResponse.data.titulo;
	    		$scope.description=dataResponse.data.descri;
	    	}
		});
		
		Service.getParams().then(function(dataResponse) {  
	    	
	    	if(dataResponse.data.error!=undefined)
	    		$scope.sendAlert(dataResponse.data.tituloError+': '+dataResponse.data.error);
	    	else{
	    		$scope.columns=dataResponse.data.data;
	    		//recorro los campos para cargar los data de los combos
	    		for(i=0; i<$scope.columns.length;i++){    			
	    			//si el tipo de dato es columna
	    			if($scope.columns[i].paratida=='CS' || $scope.columns[i].paratida=='CI'){    				    				    				
	    				//se pasa el codigo del combo
	    				Service.getCombo($scope.columns[i].paracomb).then(function(dataResponse) {    					    					
	    					if(dataResponse.data.error!=undefined)
	    						$scope.sendAlert(dataResponse.data.tituloError+': '+dataResponse.data.error);
	    			    	else{    			   
	    			    		//se carga la data en los options
	    			    		$scope.options[dataResponse.data.combo] = dataResponse.data.data;      			    		
	    			    	}
	    				});    				    				 		    				    		
	    			}
	    		}    		
	    	}
	    });	
	}
	
	//Evento del calendario
	$scope.open = function($event,opened) {
		
	    $event.preventDefault();
	    $event.stopPropagation();	    
	    
	    $scope[opened] = true;
	  }
	  	
	//evento de ejecutar el proceso
	$scope.ExecuteProcess= function(){	
		$scope.directiveGrid=false;
		$scope.trans=false;
		$scope.BotonLoader=true;
		$scope.Boton=false;		
		$scope.Error = false;
		
		var verify=true;		
		$scope.paramsSend={};	

		$scope.generateParamsSend();			
		
		if(verify){		
						
			formData=new FormData();
			
			formData.append("params", angular.toJson($scope.paramsSend));
						
			Service.ExecuteProcess(formData).then(function(dataResponse) {
				 	      
        		if(dataResponse.data.Success!=undefined){
        			$scope.trans=true;
        			$scope.sendAlert('Proceso Terminado Satisfactoriamente');
        			
        			$scope.transaccion=dataResponse.data.Success;
        			$scope.loadGrid(dataResponse.data.columns, dataResponse.data.listAll, dataResponse.data.count);
        		}
        		else{
        			$scope.Error = true;
        			$scope.sendAlert('Proceso no termino Satisfactoriamente');        			
        			$scope.DescripcionError = dataResponse.data.Eror;        		        			
				}
	        	
				$scope.BotonLoader=false;
				$scope.Boton = true;	
	        }); 						
		}else{ 
			$scope.sendAlert("Datos vacios o incorrectos: Favor diligencie todos los campos");
			$scope.BotonLoader=false;
			$scope.Boton = true;	
		}				
	}
	
	$scope.generateParamsSend = function(){	
		for(i=0; i<$scope.columns.length;i++){
			if($scope.columns[i].paratipo=='E' ){					
				//Verificar si los datos requeridos cumplen con haber sido digitados
				if(($scope.Params[$scope.columns[i].paranomb]==undefined || $scope.Params[$scope.columns[i].paranomb]=='') && $scope.Params[$scope.columns[i].paranomb]!=0){
					verify=false;
					break;
				}
				
				//Tomar solo los datos de salida para enviarlos a la consulta
				if($scope.Params[$scope.columns[i].paranomb]==undefined){
					$scope.paramsSend[$scope.columns[i].paranomb]=null;
				}
				else if($scope.columns[i].paratida=='D'){//date
					if(typeof $scope.Params[$scope.columns[i].paranomb]=="string"){
						//console.log("string");
						$scope.paramsSend[$scope.columns[i].paranomb]=$scope.Params[$scope.columns[i].paranomb];
					}
					else{
						//console.log("no string");
						$scope.paramsSend[$scope.columns[i].paranomb]=$filter('date')(new Date($scope.Params[$scope.columns[i].paranomb]), 'dd/MM/yyyy');
					}
				} else if($scope.columns[i].paratida=='T'){//timestamp
					if(typeof $scope.Params[$scope.columns[i].paranomb]=="string"){
						//console.log("string");
						$scope.paramsSend[$scope.columns[i].paranomb]=$scope.Params[$scope.columns[i].paranomb];
					}
					else{
						//console.log("no string");
						$scope.paramsSend[$scope.columns[i].paranomb]=$filter('date')(new Date($scope.Params[$scope.columns[i].paranomb]), 'dd/MM/yyyy HH:mm:ss');
					}
				} else	{
					$scope.paramsSend[$scope.columns[i].paranomb]=$scope.Params[$scope.columns[i].paranomb];
				}
			}
			
		}		
	}
	
	$scope.sendAlert = function(error){
		$scope.$broadcast('loadDataError', error);
	}
	
	$scope.loadGrid= function(columns,listAll,count){
		$scope.columnsGrid=columns;
		
		angular.forEach($scope.columnsGrid, function(reg, index) {
			columns[index]={field: reg, displayName: reg};
    	});	
		$scope.columnDefs=columns;	
		
		$scope.gridOptions = {  
			sortInfo:{ fields: ['SUCUR'], directions: ['asc']},	
	    	selectedItems: [],
	    	enableRowSelection: true,
	    	multiSelect: true,
	    	selectWithCheckboxOnly: true,
	    	showSelectionCheckbox: true
	    };
		
		$scope.directiveGrid=true;		
		
		$scope.listAll=listAll;
		$scope.count=count;
	}
		
	$scope.$on('gridEvento', function(event, pageSize, currentPage, order, searchQuery) {   
		$scope.pageSize=pageSize;
		$scope.currentPage=currentPage;
		$scope.order=order;
		$scope.searchQuery=searchQuery;
		if($scope.searchQuery==undefined)
			$scope.searchQuery=[];
		
    	if($scope.directiveGrid)
    		$scope.loadMyGrid();
    });
	
	$scope.loadMyGrid= function(){			
		$scope.$broadcast('loadDataGrid',$scope.listAll, $scope.count, $scope.count, 1);
	}
	
	$scope.ExecuteProcessCancel= function(){	
		
		$scope.trans=false;
		$scope.BotonLoader=true;
		$scope.Boton=false;		
		$scope.Error = false;
		
		$scope.$broadcast('getGrid');
					
	}
	
	$scope.$on('getGridEvento', function(event, gridOptions) {   
		
		console.log("selectedItems: ",gridOptions.selectedItems);
		$scope.sendAlert("Usted ha seleccionado "+gridOptions.selectedItems.length+" fila(s) para el proceso de cancelación");
		$scope.generateParamsSend();	
		
		formData=new FormData();
		
		formData.append("selectedItems", angular.toJson(gridOptions.selectedItems));
		formData.append("countItems", angular.toJson(gridOptions.selectedItems.length));
		formData.append("params", angular.toJson($scope.paramsSend));
		
		Service.ExecuteProcessCancel(formData).then(function(dataResponse) {
	 	      
    		if(dataResponse.data.Success!=undefined){
    			$scope.trans=true;
    			$scope.sendAlert('Proceso Terminado Satisfactoriamente');
    			
    			$scope.transaccion=dataResponse.data.Success;
    			$scope.loadGrid(dataResponse.data.columns, dataResponse.data.listAll, dataResponse.data.count);
    		}
    		else{
    			$scope.Error = true;
    			$scope.sendAlert('Proceso no termino Satisfactoriamente');        			
    			$scope.DescripcionError = dataResponse.data.Eror;        		        			
			}
        	
			$scope.BotonLoader=false;
			$scope.Boton = true;	
        });
    });
 }            
])