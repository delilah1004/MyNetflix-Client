function genreCheck(obj) {

    var genres = new Array();

    for (var i = 0; i < obj.genre.length; i++) {
        if (obj.genre[i].checked == true) {
            genres.push(obj.genre[i].value);
        }
    }

    obj.selectedGenres.value = genres;
}