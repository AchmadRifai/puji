/* global NaN */

$(document).ready(function(){
    $("#byr").keyup(function(e){
        var byr=parseInt(""+$("#byr").val()),total=parseInt(""+$("#total").text()),ba;
        ba=byr-total;
        $("#kembali").text(""+ba);
        if(ba!=NaN){
            if(ba<0)$("#jalan").toggleClass("disabled");
            else $("#jalan").removeClass("disabled");
        }else $("#jalan").toggleClass("disabled");
    });
});