function genreCheck(obj) {

	var genres = new Array();

	for (var i = 0; i < obj.genre.length; i++) {
		if (obj.genre[i].checked == true) {
			genres.push(obj.genre[i].value);
		}
	}

	obj.selectedGenres.value = genres;
}

// function page_move(url, condition, pageNumber) {

// 	var form = document.createElement("form");
// 	var param = new Array();
// 	var input = new Array();

// 	form.action = url;
// 	form.method = "post";

// 	if (pageNumber == null) {
// 		param.push(['pageNumber', pageNumber]);
// 		alert(pageNumber);
// 	}

// 	param.push(['condition', condition]);

// 	param.push(['selectedGenres', $(genres)]);

// 	if (condition != 4) {
// 		param.push(['selectedGenres', null]);
// 	}

// 	for (var i = 0; i < parm.length; i++) {
// 		input[i] = document.createElement("input");
// 		input[i].setAttribute("type", "hidden");
// 		input[i].setAttribute('name', param[i][0]);
// 		input[i].setAttribute("value", param[i][1]);
// 		form.appendChild(input[i]);
// 	}

// 	document.body.appendChild(form);
// 	form.submit();
// }

// function genreSearch() {

// }