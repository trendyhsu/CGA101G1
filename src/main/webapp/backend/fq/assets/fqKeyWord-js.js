window.onload = function() {

	let radio1 = document.getElementById("qu");
	let radio2 = document.getElementById("in");

	let form1 = document.getElementById("form1");
	let form2 = document.getElementById("form2");

	radio1.onclick = () => {
		form2.style.display = "none";
		form1.style.display = "flex";
	}
	radio2.onclick = () => {
		form1.style.display = "none";
		form2.style.display = "flex";
	}
}




