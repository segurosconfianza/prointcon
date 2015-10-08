var FrmMainApp=angular.module('FrmMainApp');

FrmMainApp.controller('CieEstaprocController', ['$scope', 'CieEstaprocService',function($scope, Service) {
    	//botones de los formularios modal
    	$scope.buttonNew=false;
    	$scope.buttonEdit=false;
    	$scope.buttonDelete=false;    	
    	
    	//Funcion para inicializar los datos en la carga de la pagina
		$scope.init = function() {
			Service.getI18n().then(function(dataResponse) {        	                                        	
        	 	
        	 	Service.setI18n(dataResponse.data);
        	 	
	        	columns=[ { field: "esprcons", displayName: getName(Service.getI18n(), "esprcons", "CIE_ESTAPROC"), visible: true } ;
{ field: "esprnomb", displayName: getName(Service.getI18n(), "esprnomb", "CIE_ESTAPROC"), visible: true } ;
{ field: "esprporc", displayName: getName(Service.getI18n(), "esprporc", "CIE_ESTAPROC"), visible: true } ;
{ field: "esprdesc", displayName: getName(Service.getI18n(), "esprdesc", "CIE_ESTAPROC"), visible: true } ;
{ field: "espreror", displayName: getName(Service.getI18n(), "espreror", "CIE_ESTAPROC"), visible: true } ;
{ field: "espruser", displayName: getName(Service.getI18n(), "espruser", "CIE_ESTAPROC"), visible: true } ;
{ field: "espresta", displayName: getName(Service.getI18n(), "espresta", "CIE_ESTAPROC"), visible: true } ;
{ field: "esprduho", displayName: getName(Service.getI18n(), "esprduho", "CIE_ESTAPROC"), visible: true } ;
{ field: "esprfein", displayName: getName(Service.getI18n(), "esprfein", "CIE_ESTAPROC"), visible: true } ;
{ field: "esprfefi", displayName: getName(Service.getI18n(), "esprfefi", "CIE_ESTAPROC"), visible: true } ];
	            
	            $scope.columnDefs=columns;
	            
	            $scope.ventanaTitulo=getName(Service.getI18n(), "-", "CIE_ESTAPROC");
	            
	            $scope.gridOptions = {  
	                	sortInfo:{ fields: ['usuacons'], directions: ['desc']},
	                	selectedItems: [],
	                    afterSelectionChange: function (rowItem, event) {
	                    	$scope.esprcons = rowItem.entity.esprcons; 
	                    	 $scope.esprnomb = rowItem.entity.esprnomb; 
	                    	 $scope.esprporc = rowItem.entity.esprporc; 
	                    	 $scope.esprdesc = rowItem.entity.esprdesc; 
	                    	 $scope.espreror = rowItem.entity.espreror; 
	                    	 $scope.espruser = rowItem.entity.espruser; 
	                    	 $scope.espresta = rowItem.entity.espresta; 
	                    	 $scope.esprduho = rowItem.entity.esprduho; 
	                    	 $scope.esprfein = rowItem.entity.esprfein; 
	                    	 $scope.esprfefi = rowItem.entity.esprfefi;    	                    	                    	   
	                    }
	                };
	            
	            //Si lleva un filtro basico la grid $scope.basicSearchQuery=[{campo: 'usuatipo', tipo: "=", val1: 1, tipodato: "Number"} ];
	            
	            $scope.directiveGrid=true;
	            
        	});        	        	
        	
		}	        
              
        function getName(i18n,colum,modulo){
	    	var log = [];
	    	angular.forEach(i18n, function(fila, index) {
	    		if(fila.etincamp==colum && fila.modunomb==modulo)  
	    			this.push(fila);
	   		}, log);
	    	
	    	return log[0].etinetiq;        	
	    }
        
        $scope.whatClassIsIt= function(column){
        	var log = [];
        	
        	angular.forEach($scope.columnDefs, function(fila, index) {
        		if(fila.field==column)
        			this.push(fila);
       		}, log);
        	
        	return log[0].displayName;
        }                
               
       $scope.createRecordForm= function(){
    	    $scope.buttonNew=true;
			$scope.buttonEdit=false;
			$scope.buttonDelete=false;
			
			$scope.esprcons = ""; 
			$scope.esprnomb = ""; 
			$scope.esprporc = ""; 
			$scope.esprdesc = ""; 
			$scope.espreror = ""; 
			$scope.espruser = ""; 
			$scope.espresta = ""; 
			$scope.esprduho = ""; 
			$scope.esprfein = ""; 
			$scope.esprfefi = "";         	
        }                
        
		$scope.loadDatatoForm= function(){			
			
			if($scope.gridOptions.selectedItems.length>0){
				$scope.buttonNew=false;
				$scope.buttonEdit=true;
				$scope.buttonDelete=false;					        	
			}
			else{
				$scope.sendAlert("Favor seleccione una fila");
				$('#myModalNew').modal('hide');		
			}	
        }                       
		
		$scope.deleteRecordForm= function(){
			
			if($scope.gridOptions.selectedItems.length>0){
				$scope.buttonNew=false;
				$scope.buttonEdit=false;
				$scope.buttonDelete=true;					        	
			}
			else{
				$scope.sendAlert("Favor seleccione una fila");
				$('#myModalNew').modal('hide');		
			}			
        }
		
		$scope.insertRecord= function(){
			
			if($scope.validateData())	
				Service.insertRecord($scope.esprcons,$scope.esprnomb,$scope.esprporc,$scope.esprdesc,$scope.espreror,$scope.espruser,$scope.espresta,$scope.esprduho,$scope.esprfein,$scope.esprfefi).then(function(dataResponse) {    
					if(dataResponse.data.error!=undefined)
						$scope.sendAlert(dataResponse.data.tituloError+': '+dataResponse.data.error);
			    	else{    	            
						row=dataResponse.data;
						
						$scope.esprcons = row.esprcons; 
						$scope.esprnomb = row.esprnomb; 
						$scope.esprporc = row.esprporc; 
						$scope.esprdesc = row.esprdesc; 
						$scope.espreror = row.espreror; 
						$scope.espruser = row.espruser; 
						$scope.espresta = row.espresta; 
						$scope.esprduho = row.esprduho; 
						$scope.esprfein = row.esprfein; 
						$scope.esprfefi = row.esprfefi;      
							
						$scope.sendAlert("Se creo el registro correctamente");		
			        	$('#myModalNew').modal('hide');
			        	$scope.loadMyGrid();
			        }
		        }); 
		    else
				$scope.sendAlert("Faltan datos por diligenciar");    
        }
		
		$scope.updateRecord= function(){
			
			if($scope.validateData())	
				Service.updateRecord($scope.esprcons,$scope.esprnomb,$scope.esprporc,$scope.esprdesc,$scope.espreror,$scope.espruser,$scope.espresta,$scope.esprduho,$scope.esprfein,$scope.esprfefi).then(function(dataResponse) { 
					if(dataResponse.data.error!=undefined)
						$scope.sendAlert(dataResponse.data.tituloError+': '+dataResponse.data.error);
			    	else{       	            
						row=dataResponse.data;
						
						$scope.esprcons = row.esprcons; 
						$scope.esprnomb = row.esprnomb; 
						$scope.esprporc = row.esprporc; 
						$scope.esprdesc = row.esprdesc; 
						$scope.espreror = row.espreror; 
						$scope.espruser = row.espruser; 
						$scope.espresta = row.espresta; 
						$scope.esprduho = row.esprduho; 
						$scope.esprfein = row.esprfein; 
						$scope.esprfefi = row.esprfefi; 
						
						$scope.sendAlert("Se actualizo el registro correctamente");
						$('#myModalNew').modal('hide');
			        	$scope.loadMyGrid();
			        }
		        });
		    else
				$scope.sendAlert("Faltan datos por diligenciar");     
        }
		
		$scope.deleteRecord= function(){
						
			Service.deleteRecord($scope.esprcons,$scope.esprnomb,$scope.esprporc,$scope.esprdesc,$scope.espreror,$scope.espruser,$scope.espresta,$scope.esprduho,$scope.esprfein,$scope.esprfefi).then(function(dataResponse) { 
				if(dataResponse.data.error!=undefined)
					$scope.sendAlert(dataResponse.data.tituloError+': '+dataResponse.data.error);
			    else{         	            
					row=dataResponse.data;
					
					$scope.esprcons = row.esprcons; 
					$scope.esprnomb = row.esprnomb; 
					$scope.esprporc = row.esprporc; 
					$scope.esprdesc = row.esprdesc; 
					$scope.espreror = row.espreror; 
					$scope.espruser = row.espruser; 
					$scope.espresta = row.espresta; 
					$scope.esprduho = row.esprduho; 
					$scope.esprfein = row.esprfein; 
					$scope.esprfefi = row.esprfefi; 
	
					$scope.sendAlert("Se borro el registro correctamente");
					$('#myModalNew').modal('hide');
		        	$scope.loadMyGrid();
		        }
	        }); 
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
			
			Service.getData($scope.pageSize, $scope.currentPage, $scope.order, $scope.searchQuery.concat($scope.basicSearchQuery)).then(function(dataResponse) {
	    		if(dataResponse.data.error!=undefined)
	    			$scope.sendAlert(dataResponse.data.tituloError+': '+dataResponse.data.error);
	        	else 
	        		$scope.$broadcast('loadDataGrid',dataResponse.data.data, dataResponse.data.count, $scope.pageSize, $scope.currentPage);
	        });
		}
		
		$scope.sendAlert = function(error){
			$scope.$broadcast('loadDataError', error);
		}
		
		$scope.validateData= function(){			
			
			if(!formInsert.$valid)
				return false;
			else if($scope.esprcons==undefined || $scope.esprcons.trim()=='')
							return false;
			else if($scope.esprnomb==undefined || $scope.esprnomb.trim()=='')
							return false;
			else if($scope.esprporc==undefined || $scope.esprporc.trim()=='')
							return false;
			else if($scope.esprdesc==undefined || $scope.esprdesc.trim()=='')
							return false;
			else if($scope.espreror==undefined || $scope.espreror.trim()=='')
							return false;
			else if($scope.espruser==undefined || $scope.espruser.trim()=='')
							return false;
			else if($scope.espresta==undefined || $scope.espresta.trim()=='')
							return false;
			else if($scope.esprduho==undefined || $scope.esprduho.trim()=='')
							return false;
			else if($scope.esprfein==undefined || $scope.esprfein.trim()=='')
							return false;
			else if($scope.esprfefi==undefined || $scope.esprfefi.trim()=='')
							return false;	
						
			return true;
		}				
    }            
    ])
