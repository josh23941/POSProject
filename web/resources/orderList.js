            
            var _filterValue = 'all';
            var _idSortToggle = 'ascending';
            var _nameSortToggle = 'ascending';
            var _timeSortToggle = 'ascending';
            var _serveSortToggle = 'ascending';
            var _tableSortToggle = 'ascending';
            
            $('document').ready(function(){
               sort('time'); 
            });
            
            function filter(param){
                if(param == 'all'){
                    $('.del, .co, .di, .delCoNull, .coDiNull, .diNull, .delDiNull').show();
                    _filterValue = 'all';
                }
                else if(param == 'del'){
                    $('.co, .di, .delCoNull, .delDiNull').hide();
                    $('.del, .coDiNull, .diNull').show();
                    _filterValue = 'del';
                }
                else if(param == 'co'){
                    $('.del, .di, .delCoNull, .coDiNull').hide();
                    $('.co, .diNull, .delDiNull').show();
                    _filterValue = 'co';
                }
                else if(param == 'di'){
                    $('.del, .co, .coDiNull, .diNull, .delDiNull').hide();
                    $('.di, .delCoNull').show();
                    _filterValue = 'di';
                }
            }
            
            function indexToClassObj(rowId, data, htmlClass){
                this.rowId = rowId;
                this.data = data;
                this.htmlClass = htmlClass;
            }
            
            function toggleSortVar(param){
                switch(param){
                    case 'id':
                        if(_idSortToggle == 'ascending'){
                            _idSortToggle = 'descending';
                        }
                        else{
                            _idSortToggle = 'ascending';
                        }
                        break;
                    case 'name':
                        if(_nameSortToggle == 'ascending'){
                            _nameSortToggle = 'descending'; 
                        }
                        else{
                            _nameSortToggle = 'ascending';
                        }
                        break;
                    case 'time':
                        if(_timeSortToggle == 'ascending'){
                            _timeSortToggle = 'descending';
                        }
                        else{
                            _timeSortToggle = 'ascending';
                        }
                        break;
                    case 'serve':
                        if(_serveSortToggle == 'ascending'){
                            _serveSortToggle = 'descending';
                        }
                        else{
                            _serveSortToggle = 'ascending';
                        }
                        break;
                    case 'table':
                        if(_tableSortToggle == 'ascending'){
                            _tableSortToggle = 'descending';
                        }
                        else{
                            _tableSortToggle = 'ascending';
                        }
                        break;
                }
            }
            function sort(param){
                var rows = 0;
                var sortMethod;
                var toggle;
                
                $('tbody tr').each(function(){
                    rows++;
                });
                var rowArray = new Array(rows);
                rows = 0;
                $('tbody tr').each(function(){
                    var id = $(this).attr('id');
                    var data;
                    switch(param){
                        case 'id': 
                            data = $('td:first', $(this)).text();
                            sortMethod = 'numeric';
                            toggle = _idSortToggle;
                            break;
                        case 'name':
                            data = $('td:eq(2)', $(this)).text();
                            sortMethod = 'alphabetic';
                            toggle = _nameSortToggle;
                            break;
                        case 'time':
                            data = $('td:eq(10)', $(this)).text();
                            sortMethod = 'numeric';
                            toggle = _timeSortToggle;
                            break;
                        case 'serve':
                            data = $('td:eq(1)', $(this)).text();
                            sortMethod = 'alphabetic';
                            toggle = _serveSortToggle;
                            break;
                        case 'table':
                            data = $('td:eq(7)', $(this)).text();
                            sortMethod = 'numeric';
                            toggle = _tableSortToggle;
                            break;
                    }
                    var rowClass = $(this).attr('class');
                    rowArray[rows] = new indexToClassObj(id, data, rowClass);
                    rows++;
                });
                if(sortMethod == 'numeric'){
                    if(toggle == 'ascending'){
                        rowArray.sort(function(a,b){
                            if(a.data != "---" && b.data != "---"){
                                return a.data-b.data;
                            }
                            else if(a.data == "---" && b.data != "---"){
                                return -1;
                            }
                            else if(a.data != "---" && b.data == "---"){
                                return 1;
                            }
                            else {
                                return 0;
                            }
                        });
                    }
                    else{
                        rowArray.sort(function(a,b){
                            if(a.data != "---" && b.data != "---"){
                                return b.data-a.data;
                            }
                            else if(a.data == "---" && b.data != "---"){
                                return 1;
                            }
                            else if(a.data != "---" && b.data == "---"){
                                return -1;
                            }
                            else{
                                return 0;
                            }
                        });
                    }
                    toggleSortVar(param);
                }
                else if(sortMethod == 'alphabetic'){
                    rowArray.sort(function(a,b){
                        if(a.data < b.data){
                            return -1;
                        }
                        if(a.data > b.data){
                            return 1;
                        }
                        else
                            return 0;
                        });        
                    if(toggle == 'descending'){
                        rowArray.reverse();
                    }
                    toggleSortVar(param);
                }
                var tableString = "";
                for(var i=0; i<rowArray.length; i++){
                    tableString += "<tr id=\"" + rowArray[i].rowId + "\" class=\"" + rowArray[i].htmlClass + "\">";
                    tableString += $('#' + rowArray[i].rowId).html();
                    tableString += "</tr>";
                }
                $('#orders tbody').html(tableString);
                filter(_filterValue);
            }
            
            function serve(orderId){
                //Set active flag in DB
                $.ajax({
                    url: "serve_order",
                    async: "true",
                    data: "orderId="+orderId,
                    type: "POST"
                });
                //Remove row from page;
                $('#' + orderId).remove();
            }
            
            
