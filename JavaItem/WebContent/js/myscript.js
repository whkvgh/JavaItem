    $(function () {

        $("nav").load("menu.html");

        //1.1 품목조회.
        $("#itemlist").click(function () {
            $("#div1").empty();
            $("#frm1").css("display","block");
            var $this = $(this).text();

            $.ajax({	
                url: "RunPage?action=itemlist",
                success: function (result) {
                    var data = JSON.parse(result);
                    var $tag = "<br><table border='1'><caption>" + $this + "</caption>";
                    $tag += "<th width=100>분류코드</th><th width=100>상품코드</th><th width=150>상품명</th><th width=60>규격</th><th width=60>단위</th>" +
                    	"<th width=80>창고코드</th><th width=80>가격</th><th align=center width=100>처리</th>";
                    for (var i = 0; i < data.length; i++) {
                        $tag += "<tr><td>" + data[i].i_group + "</td><td>"
                            + data[i].sub_group + "</td><td>"
                            + data[i].i_name + "</td><td>"
                            + data[i].i_size + "</td><td>"
                            + data[i].unit + "</td><td>"
                            + data[i].store_code + "</td><td>"
                            + data[i].price + "</td><td>"
                        	+ "<button onclick=\"editpro(\'"+ data[i].i_name+"\')\">선택</button>" + "</td></tr>";
                        }
                    $tag += "</table>";
                    $("#div1").append($tag);
                }
            });
        })
        
  //1.1.1 품목수정
        	$("#edititem").click(function (){
        		$("#div1").empty();
        		$("#frm1").css("display","block");
                var $this = $(this).text();

                $.ajax({
                    url: "RunPage?action=edititem",
                    type : "post",
                    data : $("#frm1").serialize(),
                    success: function (result) {
                    	$("#itemlist").click();
                       
                    }
                });
            })
            
  //1.1.2 품목입력
            $("#insertitem").click(function(){
           	$("#div1").empty();
            	var $this =$(this).text();
            	
            	$.ajax({
            		url: "RunPage?action=insertitem",
            		type:"post",
            		data:$("#frm1").serialize(),
            		success: function (result) {
            			$("#itemlist").click();
               }
                });
            })
            
      // 1.1.3 삭제
            $("#deleteitem").click(function(){
           	$("#div1").empty();
            	var $this =$(this).text();
            	
            	$.ajax({
            		url: "RunPage?action=deleteitem",
            		type:"post",
            		data:$("#frm1").serialize(),
            		success: function (result) {
            			$("#itemlist").click();
               }
                });
            })
//        //1.2 창고조회.
//        $("#storelist").click(function () {
//            $("#div1").empty();
//            $("#frm1").css("display","none");
//            var $this = $(this).text();
//
//            $.ajax({
//                url: "RunPage?action=storelist",
//                success: function (result) {
//                    var data = JSON.parse(result);
//                    var $tag = "<br><table border='1'><caption>" + $this + "</caption>";
//                    $tag += "<th width=100>상품코드</th><th width=200>상품명</th><th width=300>설명</th><th align=center width=100>처리</th>";
//                    for (var i = 0; i < data.length; i++) {
//                        $tag += "<tr><td>" + data[i].store_code + "</td><td>"
//                            + data[i].store_name + "</td><td>"
//                            + data[i].expla + "</td><td>"
//                            + "<button>삭제</button><button>수정</button>" + "</td></tr>";
//                    }
//                    $tag += "</table>";
//                    $("#div1").append($tag);
//
//                },
//                error: function () {
//                    console.log("error");
//                }
//            })
//        })
//         //1.1.1 창고 수정
//        	$("#upstore").click(function (){
//        		$("#div1").empty();
//        		$("#frm1").css("display","block");
//                var $this = $(this).text();
//
//                $.ajax({
//                    url: "RunPage?action=upstore",
//                    success: function (result) {
//                        var data = JSON.parse(result);
//                        var $tag = "<br><table border='1'><caption>" + $this + "</caption>";
//                        $tag += "<th width=100>창고코드</th><th width=100>창고명</th><th width=150>설명</th><th align=center width=100>처리</th>";
//                        for (var i = 0; i < data.length; i++) {
//                            $tag += "<tr><td>" + data[i].store_code + "</td><td>"
//                                + data[i].store_name + "</td><td>"
//                                + data[i].expla+ "</td><td>"
//                              	+ "<button onclick=\"upstore(\'"+ data[i].store_name+"\')\">수정</button>" + "</td></tr>";
//                  
//                        }
//                        $tag += "</table>";
//                        $("#div1").append($tag);
//                    }
//                });
//            })
//            
//        
//        //1.3 입출고 내역조회.
//        $("#inoutlist").click(function () {
//            $("#div1").empty();
//            $("#frm1").css("display","none");
//            var $this = $(this).text();
//
//            $.ajax({
//                url: "RunPage?action=inoutlist",
//                success: function (result) {
//                    var data = JSON.parse(result);
//                    var $tag = "<br><table border='1'><caption>" + $this + "</caption>";
//                    $tag += "<th width=150>상품코드</th><th width=100>상품명</th><th width=60>입고수량</th><th width=60>출고수량</th> " + 
//                    	"<th width=100>입출고일자</th><th width=100>창고명</th><th width=100>비고</th><th align=center width=100>처리</th>";
//                    for (var i = 0; i < data.length; i++) {
//                        $tag += "<tr><td>" + data[i].sub_group+ "</td><td>"
//                            + data[i].i_name + "</td><td>"
//                            + data[i].in_count + "</td><td>"
//                            + data[i].out_count + "</td><td>"
//                            + data[i].data_list + "</td><td>"
//                            + data[i].store_code + "</td><td>"
//                            + data[i].bigo + "</td><td>"
//                            + "<button>삭제</button><button>수정</button>" + "</td></tr>";
//                            
//                    }
//                    $tag += "</table>";
//                    $("#div1").append($tag);
//                }
//            });
//        })
//
//
//        //1.4 구매업체조회.
//        $("#inbusinlist").click(function () {
//            $("#div1").empty();
//            $("#frm1").css("display","none");
//            var $this = $(this).text();
//
//            $.ajax({
//                url: "RunPage?action=inbusinlist",
//                success: function (result) {
//                    var data = JSON.parse(result);
//                    var $tag = "<br><table border='1'><caption>" + $this + "</caption>";
//                    $tag += "<th width=200>업체코드</th><th width=100>업체명</th><th width=200>업체주소</th><th width=150>업체연락처</th>"+
//                    	"<th width=60>대표자명</th><th align=center width=100>처리</th>";
//                    for (var i = 0; i < data.length; i++) {
//                        $tag += "<tr><td>" + data[i].busin_code+ "</td><td>"
//                            + data[i].busin_name + "</td><td>"
//                            + data[i].busin_addr + "</td><td>"
//                            + data[i].busin_num + "</td><td>"
//                            + data[i].ceo + "</td><td>"
//                            + "<button>삭제</button><button>수정</button>" + "</td></tr>";
//                            
//                    }
//                    $tag += "</table>";
//                    $("#div1").append($tag);
//                }
//            });
//        })
//
//        //1.4 판매업체조회.
//        $("#outbusin").click(function () {
//            $("#div1").empty();
//            $("#frm1").css("display","none");
//            var $this = $(this).text();
//
//            $.ajax({
//                url: "RunPage?action=outbusin",
//                success: function (result) {
//                    var data = JSON.parse(result);
//                    var $tag = "<br><table border='1'><caption>" + $this + "</caption>";
//                    $tag += "<th width=150>업체코드</th><th width=100>업체명</th><th width=200>업체주소</th><th width=100>업체연락처</th>" + 
//                    "<th width=60>대표자명</th><th align=center width=100>처리</th>";
//                    for (var i = 0; i < data.length; i++) {
//                        $tag += "<tr><td>" + data[i].busin_code + "</td><td>"
//                        + data[i].busin_name + "</td><td>"
//                        + data[i].busin_addr + "</td><td>"
//                        + data[i].busin_num + "</td><td>"
//                        + data[i].ceo + "</td><td>"
//                        + "<button>삭제</button><button>수정</button>" + "</td></tr>";
//                    }
//                    $tag += "</table>";
//                    $("#div1").append($tag);
//                }
//            });
//        })
//
//        // 2.1 주문생성
//        $("#createPurchase").click(function () {
//            $("#div1").empty();
//            var $this = $(this).text();
//
//            $.ajax({
//                url: "RunPage?action=createPurchase",
//                success: function (result) {
//                    var data = JSON.parse(result);
//                    var $tag = "<br><table border='1'><caption>" + $this + "</caption>";
//                    $tag += "<th width=100>상품코드</th><th width=200>상품명</th><th width=100>단가</th><th width=60>규격</th><th width=60>단위</th><th width=60>창고</th><th align=center width=80>처리</th>";
//                    for (var i = 0; i < data.length; i++) {
//                        $tag += "<tr><td>" + data[i].productCode + "</td><td>"
//                            + data[i].productName + "</td><td>"
//                            + data[i].unitPrice + "</td><td>"
//                            + data[i].productSpec + "</td><td>"
//                            + data[i].productUom + "</td><td>"
//                            + data[i].warehouseCode + "</td><td>"
//                            + "<button>수정</button>" + "</td></tr>";
//                    }
//                    $tag += "</table>";
//                    $("#div1").append($tag);
//                }
//            });
//        })
//
//        // 2.2 판매생성
//        $("#createSales").click(function () {
//            $("#div1").empty();
//            var $this = $(this).text();
//
//            $.ajax({
//                url: "RunPage?action=createSales",
//                success: function (result) {
//                    var data = JSON.parse(result);
//                    var $tag = "<br><table border='1'><caption>" + $this + "</caption>";
//                    $tag += "<th width=100>상품코드</th><th width=200>상품명</th><th width=100>단가</th><th width=60>규격</th><th width=60>단위</th><th width=60>창고</th><th align=center width=80>처리</th>";
//                    for (var i = 0; i < data.length; i++) {
//                        $tag += "<tr><td>" + data[i].productCode + "</td><td>"
//                            + data[i].productName + "</td><td>"
//                            + data[i].unitPrice + "</td><td>"
//                            + data[i].productSpec + "</td><td>"
//                            + data[i].productUom + "</td><td>"
//                            + data[i].warehouseCode + "</td><td>"
//                            + "<button>수정</button>" + "</td></tr>";
//                    }
//                    $tag += "</table>";
//                    $("#div1").append($tag);
//                }
//            });
//        })
//
//        // 2.3 주문조회
//        $("#inlist").click(function () {
//            $("#div1").empty();
//            var $this = $(this).text();
//
//            $.ajax({
//                url: "RunPage?action=inlist",
//                success: function (result) {
//                    var data = JSON.parse(result);
//                    console.log(data);
//                    var $tag = "<br><table border='1'><caption>" + $this + "</caption>";
//                    $tag += "<th width=100>구매번호</th><th width=100>라인번호</th><th width=100>상품코드</th><th width=100>상품명</th>"+
//                    "<th width=60>수량</th><th width=80>단가</th><th width=100>금액</th><th width=150>입고일자</th>"+
//                    "<th width=80>처리</th>";
//                    for (var i = 0; i < data.length; i++) {
//                        $tag += "<tr><td>" + data[i].buy_num + "</td><td>"
//                            + data[i].sub_info + "</td><td>"
//                            + data[i].sub_group + "</td><td>"
//                            + data[i].i_name + "</td><td>"
//                            + data[i].i_count + "</td><td>"
//                            + data[i].price + "</td><td>"
//                            + data[i].money+ "</td><td>"
//                            + data[i].in_date+ "</td><td>"
//                            + data[i].busin_code+ "</td><td>"
//                            + data[i].store_code+ "</td><td>"
//                            + "<button>수정</button>" + "</td></tr>";
//                    }
//                    $tag += "</table>";
//                    $("#div1").append($tag);
//                }
//            });
//        })
//
//        // 2.4 판매조회
//        $("#selectSales").click(function () {
//            $("#div1").empty();
//            var $this = $(this).text();
//
//            $.ajax({
//                url: "RunPage?action=selectSales",
//                success: function (result) {
//                    var data = JSON.parse(result);
//                    var $tag = "<br><table border='1'><caption>" + $this + "</caption>";
//                    $tag += "<th width=100>상품코드</th><th width=200>상품명</th><th width=100>단가</th><th width=60>규격</th><th width=60>단위</th><th width=60>창고</th><th align=center width=80>처리</th>";
//                    for (var i = 0; i < data.length; i++) {
//                        $tag += "<tr><td>" + data[i].productCode + "</td><td>"
//                            + data[i].productName + "</td><td>"
//                            + data[i].unitPrice + "</td><td>"
//                            + data[i].productSpec + "</td><td>"
//                            + data[i].productUom + "</td><td>"
//                            + data[i].warehouseCode + "</td><td>"
//                            + "<button>수정</button>" + "</td></tr>";
//                    }
//                    $tag += "</table>";
//                    $("#div1").append($tag);
//                }
//            });
//        })
//
//        // 3.1 입고정보 조회
//        $("#selectInput").click(function () {
//            $("#div1").empty();
//            var $this = $(this).text();
//
//            $.ajax({
//                url: "RunPage?action=selectInput",
//                success: function (result) {
//                    var data = JSON.parse(result);
//                    var $tag = "<br><table border='1'><caption>" + $this + "</caption>";
//                    $tag += "<th width=100>상품코드</th><th width=200>상품명</th><th width=100>단가</th><th width=60>규격</th><th width=60>단위</th><th width=60>창고</th><th align=center width=80>처리</th>";
//                    for (var i = 0; i < data.length; i++) {
//                        $tag += "<tr><td>" + data[i].productCode + "</td><td>"
//                            + data[i].productName + "</td><td>"
//                            + data[i].unitPrice + "</td><td>"
//                            + data[i].productSpec + "</td><td>"
//                            + data[i].productUom + "</td><td>"
//                            + data[i].warehouseCode + "</td><td>"
//                            + "<button>수정</button>" + "</td></tr>";
//                    }
//                    $tag += "</table>";
//                    $("#div1").append($tag);
//                }
//            });
//        })
//
//        // 3.2 출고정보 조회
//        $("#selectOuptput").click(function () {
//            $("#div1").empty();
//            var $this = $(this).text();
//
//            $.ajax({
//                url: "RunPage?action=selectOuptput",
//                success: function (result) {
//                    var data = JSON.parse(result);
//                    var $tag = "<br><table border='1'><caption>" + $this + "</caption>";
//                    $tag += "<th width=100>상품코드</th><th width=200>상품명</th><th width=100>단가</th><th width=60>규격</th><th width=60>단위</th><th width=60>창고</th><th align=center width=80>처리</th>";
//                    for (var i = 0; i < data.length; i++) {
//                        $tag += "<tr><td>" + data[i].productCode + "</td><td>"
//                            + data[i].productName + "</td><td>"
//                            + data[i].unitPrice + "</td><td>"
//                            + data[i].productSpec + "</td><td>"
//                            + data[i].productUom + "</td><td>"
//                            + data[i].warehouseCode + "</td><td>"
//                            + "<button>수정</button>" + "</td></tr>";
//                    }
//                    $tag += "</table>";
//                    $("#div1").append($tag);
//                }
//            });
//        })
//
//        // 3.3 품목별 재고조회
//        $("#selectItemOnhand").click(function () {
//            $("#div1").empty();
//            var $this = $(this).text();
//
//            $.ajax({
//                url: "RunPage?action=selectItemOnhand",
//                success: function (result) {
//                    var data = JSON.parse(result);
//                    var $tag = "<br><table border='1'><caption>" + $this + "</caption>";
//                    $tag += "<th width=100>상품코드</th><th width=200>상품명</th><th width=100>단가</th><th width=60>규격</th><th width=60>단위</th><th width=60>창고</th><th width=60>재고량</th><th align=center width=80>처리</th>";
//                    for (var i = 0; i < data.length; i++) {
//                        $tag += "<tr><td>" + data[i].productCode + "</td><td>"
//                            + data[i].productName + "</td><td>"
//                            + data[i].unitPrice + "</td><td>"
//                            + data[i].productSpec + "</td><td>"
//                            + data[i].productUom + "</td><td>"
//                            + data[i].warehouseCode + "</td><td>"
//                            + data[i].onhandQty + "</td><td>"
//                            + "<button>수정</button>" + "</td></tr>";
//                    }
//                    $tag += "</table>";
//                    $("#div1").append($tag);
//                }
//            });
//        })
//
//        // 3.4 창고별 재고조회
//        $("#selectWarehouseOnhand").click(function () {
//            $("#div1").empty();
//            var $this = $(this).text();
//
//            $.ajax({
//                url: "RunPage?action=selectWarehouseOnhand",
//                success: function (result) {
//                    var data = JSON.parse(result);
//                    var $tag = "<br><table border='1'><caption>" + $this + "</caption>";
//                    $tag += "<th width=60>창고</th><th width=100>상품코드</th><th width=200>상품명</th><th width=100>단가</th><th width=60>규격</th><th width=60>단위</th><th width=60>재고량</th><th align=center width=80>처리</th>";
//                    for (var i = 0; i < data.length; i++) {
//                        $tag += "<tr><td>" + data[i].productCode + "</td><td>"
//                            + data[i].productName + "</td><td>"
//                            + data[i].unitPrice + "</td><td>"
//                            + data[i].productSpec + "</td><td>"
//                            + data[i].productUom + "</td><td>"
//                            + data[i].warehouseCode + "</td><td>"
//                            + data[i].onhandQty + "</td><td>"
//                            + "<button>수정</button>" + "</td></tr>";
//                    }
//                    $tag += "</table>";
//                    $("#div1").append($tag);
//                }
//            })
//        })
    })