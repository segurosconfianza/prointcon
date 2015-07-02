var FrmMainApp=angular.module('FrmMainApp');

FrmMainApp.controller('PilUsuaanalisisController', ['$scope', 'PilUsuaanalisisService',function($scope, Service) {
    	//botones de los formularios modal
    	$scope.buttonNew=false;
    	$scope.buttonEdit=false;
    	$scope.buttonDelete=false;    	    	                          
              
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
        	
        	if(log[0]!=undefined)
        		return log[0].displayName;
        	else
        		return "";    
        }                  
               
       $scope.createRecordForm= function(){
    	    $scope.buttonNew=true;
			$scope.buttonEdit=false;
			$scope.buttonDelete=false;
			
			$scope.usuacons = "" ,
			 $scope.usuaunit = "" ,
			 $scope.usuadive = "" ,
			 $scope.usuatiin = "" ,
			 $scope.usuarazo = "" ,
			 $scope.usuanomb = "" ,
			 $scope.usuaapel = "" ,
			 $scope.usuaemai = "" ,
			 $scope.usuatele = "" ,
			 $scope.usuapeco = "" ,
			 $scope.usuausua = "" ,
			 $scope.usuapass = "" ,
			 $scope.usuatipo = 2 ,
			 $scope.usuasucu = "" ,
			 $scope.usuaesta = 'A'         	
        }                
        
		$scope.loadDatatoForm= function(){			
			
			if($scope.gridOptions.selectedItems.length>0){
				$scope.buttonNew=false;
				$scope.buttonEdit=true;
				$scope.buttonDelete=false;					        	
			}
			else
				alert("Favor seleccione una fila");
        }                       
		
		$scope.deleteRecordForm= function(){
			
			if($scope.gridOptions.selectedItems.length>0){
				$scope.buttonNew=false;
				$scope.buttonEdit=false;
				$scope.buttonDelete=true;					        	
			}
			else
				alert("Favor seleccione una fila");
        }
		
		$scope.insertRecord= function(){						
			
			Service.insertRecord($scope.usuacons,$scope.usuaunit,$scope.usuadive,$scope.usuatiin,$scope.usuarazo,$scope.usuanomb,$scope.usuaapel,$scope.usuaemai,$scope.usuatele,$scope.usuapeco,$scope.usuausua,$scope.usuapass,$scope.usuatipo,$scope.usuasucu,$scope.usuaesta).then(function(dataResponse) {        	            
				row=dataResponse.data;
				
				$scope.usuacons = row.usuacons ,
				 $scope.usuaunit = row.usuaunit ,
				 $scope.usuadive = row.usuadive ,
				 $scope.usuatiin = row.usuatiin ,
				 $scope.usuarazo = row.usuarazo ,
				 $scope.usuanomb = row.usuanomb ,
				 $scope.usuaapel = row.usuaapel ,
				 $scope.usuaemai = row.usuaemai ,
				 $scope.usuatele = row.usuatele ,
				 $scope.usuapeco = row.usuapeco ,
				 $scope.usuausua = row.usuausua ,
				 $scope.usuapass = row.usuapass ,
				 $scope.usuatipo = row.usuatipo ,
				 $scope.usuasucu = row.usuasucu ,
				 $scope.usuaesta = row.usuaesta      
								
	        	alert("Se creo el registro correctamente");
				$scope.loadMyGrid();
	        }); 
        }
		
		$scope.updateRecord= function(){
			
			Service.updateRecord($scope.usuacons,$scope.usuaunit,$scope.usuadive,$scope.usuatiin,$scope.usuarazo,$scope.usuanomb,$scope.usuaapel,$scope.usuaemai,$scope.usuatele,$scope.usuapeco,$scope.usuausua,$scope.usuapass,$scope.usuatipo,$scope.usuasucu,$scope.usuaesta).then(function(dataResponse) {        	            
				row=dataResponse.data;
				
				$scope.usuacons = row.usuacons ,
				 $scope.usuaunit = row.usuaunit ,
				 $scope.usuadive = row.usuadive ,
				 $scope.usuatiin = row.usuatiin ,
				 $scope.usuarazo = row.usuarazo ,
				 $scope.usuanomb = row.usuanomb ,
				 $scope.usuaapel = row.usuaapel ,
				 $scope.usuaemai = row.usuaemai ,
				 $scope.usuatele = row.usuatele ,
				 $scope.usuapeco = row.usuapeco ,
				 $scope.usuausua = row.usuausua ,
				 $scope.usuapass = row.usuapass ,
				 $scope.usuatipo = row.usuatipo ,
				 $scope.usuasucu = row.usuasucu ,
				 $scope.usuaesta = row.usuaesta 
									        
	        	alert("Se actualizo el registro correctamente");
				
				$scope.loadMyGrid();
	        }); 
        }
		
		$scope.deleteRecord= function(){
						
			Service.deleteRecord($scope.usuacons,$scope.usuaunit,$scope.usuadive,$scope.usuatiin,$scope.usuarazo,$scope.usuanomb,$scope.usuaapel,$scope.usuaemai,$scope.usuatele,$scope.usuapeco,$scope.usuausua,$scope.usuapass,$scope.usuatipo,$scope.usuasucu,$scope.usuaesta).then(function(dataResponse) {        	            
				row=dataResponse.data;
				
				$scope.usuacons = row.usuacons ,
				$scope.usuaunit = row.usuaunit ,
				$scope.usuadive = row.usuadive ,
				$scope.usuatiin = row.usuatiin ,
				$scope.usuarazo = row.usuarazo ,
				$scope.usuanomb = row.usuanomb ,
				$scope.usuaapel = row.usuaapel ,
				$scope.usuaemai = row.usuaemai ,
				$scope.usuatele = row.usuatele ,
				$scope.usuapeco = row.usuapeco ,
				$scope.usuausua = row.usuausua ,
				$scope.usuapass = row.usuapass ,
				$scope.usuatipo = row.usuatipo ,
				$scope.usuasucu = row.usuasucu ,
				$scope.usuaesta = row.usuaesta 

	        	alert("Se borro el registro correctamente");
				
				$scope.loadMyGrid();
	        }); 
        }	
		
		//funcion del inicializar componentes
		$scope.init = function() {
			
			Service.loadI18n().then(function(dataResponse) {        	                                        	
		       	 
				Service.setI18n(dataResponse.data);
				Service.prepForLoadI18n();	               	                                        
	        	 
	        	columns=[ 	{ field: "usuacons", displayName: getName(Service.getI18n(), "usuacons", "PIL_USUA"), visible: false, headerCellTemplate: filterBetweenNumber } ,
							{ field: "usuaunit", displayName: getName(Service.getI18n(), "usuaunit", "PIL_USUA"), visible: false, headerCellTemplate: filterText } ,
							{ field: "usuadive", displayName: getName(Service.getI18n(), "usuadive", "PIL_USUA"), visible: false, headerCellTemplate: filterText } ,
							{ field: "usuatiin", displayName: getName(Service.getI18n(), "usuatiin", "PIL_USUA"), visible: false, headerCellTemplate: filterText } ,
							{ field: "usuarazo", displayName: getName(Service.getI18n(), "usuarazo", "PIL_USUA"), visible: false, headerCellTemplate: filterText } ,
							{ field: "usuanomb", displayName: getName(Service.getI18n(), "usuanomb", "PIL_USUA"), visible: false, headerCellTemplate: filterText } ,
							{ field: "usuaapel", displayName: getName(Service.getI18n(), "usuaapel", "PIL_USUA"), visible: false, headerCellTemplate: filterText } ,
							{ field: "usuaemai", displayName: getName(Service.getI18n(), "usuaemai", "PIL_USUA"), visible: false, headerCellTemplate: filterText } ,
							{ field: "usuatele", displayName: getName(Service.getI18n(), "usuatele", "PIL_USUA"), visible: false, headerCellTemplate: filterText } ,
							{ field: "usuapeco", displayName: getName(Service.getI18n(), "usuapeco", "PIL_USUA"), visible: false, headerCellTemplate: filterText } ,
							{ field: "usuausua", displayName: getName(Service.getI18n(), "usuausua", "PIL_USUA"), visible: true, headerCellTemplate: filterText } ,
							{ field: "usuapass", displayName: getName(Service.getI18n(), "usuapass", "PIL_USUA"), visible: false, headerCellTemplate: filterText } ,
							{ field: "usuatipo", displayName: getName(Service.getI18n(), "usuatipo", "PIL_USUA"), visible: false, headerCellTemplate: filterText } ,
							{ field: "usuasucu", displayName: getName(Service.getI18n(), "usuasucu", "PIL_USUA"), visible: false, headerCellTemplate: filterText } ,
							{ field: "usuaesta", displayName: getName(Service.getI18n(), "usuaesta", "PIL_USUA"), visible: false, headerCellTemplate: filterText }  
	                 ];
	            
	            $scope.columnDefs=columns;
	            
	            $scope.ventanaTitulo=getName(dataResponse.data, "-", "PIL_USUA");
	            
	            $scope.gridOptions = {  
	                	sortInfo:{ fields: ['usuacons'], directions: ['desc']},
	                	selectedItems: [],
	                    afterSelectionChange: function (rowItem, event) {
	                    	 $scope.usuacons= rowItem.entity.usuacons;
		                   	 $scope.usuaunit= rowItem.entity.usuaunit;
		                   	 $scope.usuadive= rowItem.entity.usuadive;
		                   	 $scope.usuatiin= rowItem.entity.usuatiin;
		                   	 $scope.usuarazo= rowItem.entity.usuarazo;
		                   	 $scope.usuanomb= rowItem.entity.usuanomb;
		                   	 $scope.usuaapel= rowItem.entity.usuaapel;
		                   	 $scope.usuaemai= rowItem.entity.usuaemai;
		                   	 $scope.usuatele= rowItem.entity.usuatele;
		                   	 $scope.usuapeco= rowItem.entity.usuapeco;
		                   	 $scope.usuausua= rowItem.entity.usuausua;
		                   	 $scope.usuapass= rowItem.entity.usuapass;
		                   	 $scope.usuatipo= rowItem.entity.usuatipo;
		                   	 $scope.usuasucu= rowItem.entity.usuasucu;
		                   	 $scope.usuaesta= rowItem.entity.usuaesta;    
		                   	 
		                   	Service.prepForLoad(rowItem.entity.usuacons); 
	                    }
	                };
	            
	            $scope.basicSearchQuery=[{campo: 'usuatipo', tipo: "=", val1: 2, tipodato: "Number"} ];
	            	            
	            $scope.directiveGrid=true;
	        });	        			
			
			Service.getCombo("usuatiin").then(function(dataResponse) {  
				
				if(dataResponse.data.error!=undefined)
		    		alert(dataResponse.data.tituloError+': '+dataResponse.data.error);
		    	else{
					$scope.optionsUsuatiin = dataResponse.data;
					$scope.usuatiin = $scope.optionsUsuatiin[1];
				}
	        }); 
			
			Service.getCombo("usuaesta").then(function(dataResponse) {  
				if(dataResponse.data.error!=undefined)
		    		alert(dataResponse.data.tituloError+': '+dataResponse.data.error);
		    	else{				
					$scope.optionsUsuaesta = dataResponse.data;
					$scope.usuatiin = $scope.optionsUsuaesta[1];
				}
	        });

			Service.getSucursales(5).then(function(dataResponse) {    					    					
				if(dataResponse.data.error!=undefined)
		    		alert(dataResponse.data.tituloError+': '+dataResponse.data.error);
		    	else{    			   
		    		$scope.optionsSucursales = dataResponse.data.data;   
		    		$scope.usuaesta = $scope.optionsSucursales[1];
		    	}
			});
		}
		
		$scope.$on('gridEvento', function(event, pageSize, currentPage, order, searchQuery) {   
			$scope.pageSize=pageSize;
			$scope.currentPage=currentPage;
			$scope.order=order;
			$scope.searchQuery=searchQuery;
			
	    	if($scope.directiveGrid)
	    		$scope.loadMyGrid();
	    });
		
		$scope.loadMyGrid= function(){	
			
			Service.getData($scope.pageSize, $scope.currentPage, $scope.order, $scope.searchQuery.concat($scope.basicSearchQuery)).then(function(dataResponse) {
	    		if(dataResponse.data.error!=undefined)
	    			alert(dataResponse.data.tituloError+': '+dataResponse.data.error);
	        	else 
	        		$scope.$broadcast('loadDataGrid',dataResponse.data.data, dataResponse.data.count, $scope.pageSize, $scope.currentPage);
	        });
		}
				
    }            
    ])
