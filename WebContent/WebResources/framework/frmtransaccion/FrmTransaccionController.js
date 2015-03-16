var FrmMainApp=angular.module('FrmMainApp');

FrmMainApp.controller('FrmTransaccionController', ['$scope', 'FrmTransaccionService',function($scope, FrmTransaccionService) {
    	//botones de los formularios modal
    	$scope.buttonNew=false;
    	$scope.buttonEdit=false;
    	$scope.buttonDelete=false;    	
    	
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
    	        
              
        function getName(i18n,colum){
        	var log = [];
        	angular.forEach(i18n, function(fila, index) {
        		if(fila.etincamp==colum)  
        			this.push(fila);
       		}, log);
        	
        	return log[0].etinetiq;
        				         
        }
        
        FrmTransaccionService.getI18n().then(function(dataResponse) {        	                                        	
        	 
        	columns=[ { field: "peficons", displayName: getName(dataResponse.data, "peficons"), visible: false },
                   { field: "pefinomb", displayName: getName(dataResponse.data, "pefinomb") }, 
                   { field: "pefidesc", displayName: getName(dataResponse.data, "pefidesc") }, 
                   { field: "pefifecr", displayName: getName(dataResponse.data, "pefifecr"), visible: false },
                   { field: "pefiesta", displayName: getName(dataResponse.data, "pefiesta"), visible: false }
                 ];
            
            $scope.columnDefs=columns;
            
            $scope.ventanaTitulo=getName(dataResponse.data, "-");
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
                	FrmTransaccionService.prepForLoad(rowItem.entity.peficons, rowItem.entity.pefinomb);                    
                }
        };                              
                     				
    }            
    ])