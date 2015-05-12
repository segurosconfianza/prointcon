var FrmMainApp=angular.module('FrmMainApp');

FrmMainApp.controller('FrmTransaccionController', ['$scope', 'FrmTransaccionService',function($scope, FrmTransaccionService) {

        $scope.filterOptions = {
            filterText: "",
            useExternalFilter: true
        };
        
        $scope.totalServerItems = 0;
        
        $scope.pagingOptions = {
            pageSizes: [5, 10, 20, 50],
            pageSize: 50,
            currentPage: 1
        };  
        
        $scope.setPagingData = function(data, page, pageSize){	            
            $scope.myData = data;
            $scope.totalServerItems = data.length;
            if (!$scope.$$phase) {
                $scope.$apply();
            }
        };
        
        $scope.getPagedDataAsync = function (pageSize, page, searchText) {
            setTimeout(function () {
                var data;
                if (searchText) {
                    var ft = searchText.toLowerCase();
                    
                    FrmTransaccionService.getData(pageSize, page).then(function(dataResponse) {  
                    	
                    	if(dataResponse.data.error!=undefined)
                    		alert(dataResponse.data.tituloError+': '+dataResponse.data.error);
                    	else{                    	
	        	            data = dataResponse.data.data.filter(function(item) {
	                            return JSON.stringify(item).toLowerCase().indexOf(ft) != -1;
	                        });
	                        $scope.setPagingData(data,page,pageSize);
	                        $scope.totalServerItems = dataResponse.data.count;
                    	}
        	        });   
                } else {
                	FrmTransaccionService.getData(pageSize, page).then(function(dataResponse) {
                		if(dataResponse.data.error!=undefined)
                			alert(dataResponse.data.tituloError+': '+dataResponse.data.error);
                    	else{ 
                    		$scope.setPagingData(dataResponse.data.data,page,pageSize);
                    		$scope.totalServerItems = dataResponse.data.count;
                    	}
        	        });    
                }
            }, 100);
        };
    	
        $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
    	
        $scope.$watch('pagingOptions', function (newVal, oldVal) {
            if (newVal !== oldVal && newVal.currentPage !== oldVal.currentPage) {
              $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterOptions.filterText);
            }
        }, true);
        
        $scope.$watch('filterOptions', function (newVal, oldVal) {
            if (newVal !== oldVal) {
              $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterOptions.filterText);
            }
        }, true);
    	        
              
        function getName(i18n,colum,modulo){
        	var log = [];
        	angular.forEach(i18n, function(fila, index) {
        		if(fila.etincamp==colum && fila.modunomb==modulo)  
        			this.push(fila);
       		}, log);
        	
        	return log[0].etinetiq;
        				         
        }
        
        FrmTransaccionService.loadI18n().then(function(dataResponse) {        	                                        	
        	 
        	columns=[ { field: "trancons", displayName: getName(dataResponse.data, "trancons", "FRM_TRANSACCION") },
                   { field: "transesi", displayName: getName(dataResponse.data, "transesi", "FRM_TRANSACCION") }, 
                   { field: "tranfecr", displayName: getName(dataResponse.data, "tranfecr", "FRM_TRANSACCION") }                   
                 ];
            
            $scope.columnDefs=columns;
            
            $scope.ventanaTitulo=getName(dataResponse.data, "-", "FRM_TRANSACCION");
            
            FrmTransaccionService.setI18n(dataResponse.data);
            FrmTransaccionService.prepForLoadI18n();
        });
        
        $scope.whatClassIsIt= function(column){
        	var log = [];
        	
        	angular.forEach($scope.columnDefs, function(fila, index) {
        		if(fila.field==column)
        			this.push(fila);
       		}, log);
        	
        	return log[0].displayName;
        }
        
        $scope.gridOptions = {
                data: 'myData',
                enablePaging: true,
                showFooter: true,
                totalServerItems:'totalServerItems',
                pagingOptions: $scope.pagingOptions,
                filterOptions: $scope.filterOptions,
                multiSelect: false,
                columnDefs:'columnDefs',
                selectedItems: [],                
                showColumnMenu: true,
                enableColumnResize: true,
                showFilter : true,
                afterSelectionChange: function (rowItem, event) {                	
                	FrmTransaccionService.prepForLoad(rowItem.entity.trancons);                    
                }
        };                              
                
    }            
    ])