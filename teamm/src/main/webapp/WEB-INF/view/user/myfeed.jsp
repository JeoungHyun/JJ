<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ include file = "/WEB-INF/view/jspHeader.jsp" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" 
	src="http://www.chartjs.org/dist/2.9.3/Chart.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">

function graph_open(url){
	var op ="width=800,height=800,scrollbars=yes,left=50,top=150";
	window.open(url+".shop","graph2",op);
}
</script>
<script>
// Get the Sidebar
var mySidebar = document.getElementById("mySidebar");

// Get the DIV with overlay effect
var overlayBg = document.getElementById("myOverlay");

// Toggle between showing and hiding the sidebar, and add overlay effect
function w3_open() {
  if (mySidebar.style.display === 'block') {
    mySidebar.style.display = 'none';
    overlayBg.style.display = "none";
  } else {
    mySidebar.style.display = 'block';
    overlayBg.style.display = "block";
  }
}

// Close the sidebar with the close button
function w3_close() {
  mySidebar.style.display = "none";
  overlayBg.style.display = "none";
}
</script>
<script type="text/javascript">
	var randomColorFactor = function(){
		return Math.round(Math.random() * 255);
	}
	var randomColor = function(opa){
		return "rgba(" + randomColorFactor() + ","
				+ randomColorFactor() + ","
				+ randomColorFactor() + ","
				+ (opa || '.3') + ")";
	}
	$(function(){
		
 		bargraph();

	})
	


	function bargraph(){
		$.ajax("${path}/ajax/graph2.shop",{ //최근 7일간의 게시물 등록 건수
			success: function(data){
				barGraphPrint(data);
			},
			error: function(e){
				alert("서버 오류:" + e.status);
			}
		})
	}

	
	function barGraphPrint(data){
		console.log(data)
		var rows = JSON.parse(data);
		var dates = []
		var datas = []
		var colors = []
		$.each(rows,function(index,item){
			dates[index] = item.regdate;
			datas[index] = item.cnt;
			colors[index] = randomColor(1);
		})
		var config = {
				type: 'bar',
				data: {	labels: dates,
						datasets:[
						{
							type: 'line',
							borderColor: colors,
							borderWidth: 2,
							label: '건수',
							fill: false,
							data: datas
						},{
							type: 'bar',
							label: '건수',
							backgroundColor: colors,
							data: datas,
							borderWidth: 2
						}]
				},
				options:{
					responsive: true,
					title: {display: true,
							text: '최근 팔로워 현황',
							position: 'bottom'
					},
					legend: {display: false},
					scales:{
						xAxes:[{
							display: true,
							scaleLabel: {
								display: true,
								labelString: "날짜"
							},
							stacked: true	
						}],
						yAxes: [{
							display: true,
							scaleLabel: {
								display: true,
								labelString: "팔로워 수"
							},
							stacked: true	//기본값 0부터 시작
						}]
					}
				}
			};
			var ctx = document.getElementById("canvas2").getContext("2d");
			new Chart(ctx, config);
	}
</script>


<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
방문한 피드 : ${user } <br>
내 지금 접속한 아이디: ${ loginUser.id} <br>

팔로워 수 :  ${follower_num }<br>
팔로우 수 :  ${ follow_num }<br>

<c:if test="${loginUser.name == user.name }">
<input type="button" value="팔로우 현황 보기" onclick="graph_open('graph2')">
</c:if>


<c:if test="${loginUser.name != user.name }">
<c:if test="${follow_check ==0 }">
<form action="follow.shop" method="post">
	<input type="hidden" name="loginuserid" value="${loginUser.name }">
	<input type="hidden" name="feeduserid" value="${user.name }">
	<input type="submit"  value="팔로우하기">
 </form>
 </c:if>
</c:if>



<c:if test="${loginUser.name != user.name }">
<c:if test="${follow_check == 1 }">
<form action="unfollow.shop" method="post">
	<input type="hidden" name="loginuserid" value="${loginUser.name }">
	<input type="hidden" name="feeduserid" value="${user.name }">
	<input type="submit"  value="언팔로우하기">
 </form>
</c:if>
</c:if>


		<div class="w3-half">
			<div class="w3-container w3-padding-16">
				<div id="barcontatiner" style="width:80%; border: 1px solid #000000">
				<canvas id="canvas2" style="width:100%;"></canvas>
				</div>			
			</div>
		</div>



</body>
</html>