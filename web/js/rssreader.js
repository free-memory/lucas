/**
 * This file is for RSS Reader presentation control
 * Created by ken on 15/9/27.
 */

var artcontent = $("#articlecontent");

artcontent.dblclick(function () {
    if (artcontent.hasClass("span6"))
    {
        artcontent.removeClass().addClass("span11");
        $("#sitelist").hide();
        $("#articlelist").hide();
    }
    else
    {
        artcontent.removeClass().addClass("span6");
        $("#sitelist").show();
        $("#articlelist").show();
    }
});

