var FrmMainApp=angular.module('FrmMainApp');

FrmMainApp.controller('PilSucuranalisisController', ['$scope', 'PilSucuranalisisService',function($scope, Service) {    		    	                         
              
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
                     
		//funcion del inicializar componentes
		$scope.init = function() {
			
			Service.loadI18n().then(function(dataResponse) {        	                                        	
		       	 
				Service.setI18n(dataResponse.data);
				Service.prepForLoadI18n();	               	                                        
	        	 
				columns=[ { field: "sucucons", displayName: getName(Service.getI18n(), "sucucons", "OSI_SUCURSAL"), visible: true, headerCellTemplate: filterBetweenNumber } ,
				          { field: "sucunomb", displayName: getName(Service.getI18n(), "sucunomb", "OSI_SUCURSAL"), visible: true , headerCellTemplate: filterText}  
				        ];
				          	            
  	            $scope.columnDefs=columns;
  	            
  	            $scope.ventanaTitulo=getName(Service.getI18n(), "-", "OSI_SUCURSAL");
  	            
  	            $scope.gridOptions = {  
                	sortInfo:{ fields: ['sucucons'], directions: ['asc']},
                	selectedItems: [],
                    afterSelectionChange: function (rowItem, event) {
                    	if($scope.sucucons!=rowItem.entity.sucucons)
                    		Service.prepForLoad(rowItem.entity.sucucons, rowItem.entity.sucunomb);
                    	
                    	$scope.sucucons = rowItem.entity.sucucons; 
                    	$scope.sucunomb = rowItem.entity.sucunomb;
                    }
                };
	            	                      
	            $scope.directiveGrid=true;
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
			
			Service.getData($scope.pageSize, $scope.currentPage, $scope.order, $scope.searchQuery).then(function(dataResponse) {
	    		if(dataResponse.data.error!=undefined)
	    			$scope.sendAlert(dataResponse.data.tituloError+': '+dataResponse.data.error);
	        	else 
	        		$scope.$broadcast('loadDataGrid',dataResponse.data.data, dataResponse.data.count, $scope.pageSize, $scope.currentPage);
	        });
		}
		
		$scope.sendAlert = function(error){
			$scope.$broadcast('loadDataError', error);
		}
				
    }            
    ])
