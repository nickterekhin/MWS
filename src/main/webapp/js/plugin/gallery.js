/**
 * Created by Nick on 24.05.2015.
 */

var Gallery = function(cid,driver){
    cid=cid||0;
    this.upid=0;
    this.sqlim=0;
    this.cid = cid;
    this.num;
    this.driver = driver||'/ajax/gallery';
    this.sqldsc=0;
    this.descr_text;
    this.no_dlg=0;
    if(cid!=0)$(document).tooltip({show:{effect:"slidUp",delay:50},position:{my:"left+5 bottom-10",at:"left"}});
        $("#galldesr_dlg").dialog({
            autoOpen:false,
            width: 500,
            height: 210,
            modal: true,
            show:'fade',
            hide:'fade',
            resizable:false,
            buttons: {
                "Submit": function() {

                    gl.AddMessage();
                },
                "Cancel": function() {

                    $(this).dialog("close");
                    //$("#form_galdescr").reset();
                }
            }

        });
};

Gallery.prototype.chColorOn = function(id)
{


    $("#f"+id+"").animate({backgroundColor:'#FFcc00',borderColor:"#FF6600"},100);
};

Gallery.prototype.chColorOff=function(id)
{


    $("#f"+id+"").animate({backgroundColor:'#fff',borderColor:"#999"},100);
};

Gallery.prototype.chgColorOn = function(id,sq)
{

    if(!sq)
    {
        $("#"+id+"").animate({backgroundColor:'#666'},400);
    }

};

Gallery.prototype.chgColorOff=function(id,sq)
{
    if(!sq)
    {
        $("#"+id+"").animate({backgroundColor:"#c0c0c0"},400);
    }

};

Gallery.prototype.uploadFile=function(id,divid)
{
    this.upid=id;
    $('#fileToUpload_'+divid).click();

};

Gallery.prototype.ajaxFileUpload = function(updir,divid,resize,fl,isdescr){

    isdescr=isdescr||0;

    $("#loading_" + divid).ajaxStart(function(){
        $(this).show();

        document.getElementById('buttonUpload_' + divid).value = 'Please whait......';
        document.getElementById('buttonUpload_' + divid).disabled = true;

    }).ajaxComplete(function(){
        $(this).hide();
        document.getElementById('buttonUpload_' + divid).value = 'Upload file to server';
        document.getElementById('buttonUpload_' + divid).disabled = false;
    });
    this.decode = function(json){

    }
    $.ajaxFileUpload
    (
        {
            url: this.driver+'?a=upload&updir=' + updir + '&divid=' + divid+'&flatId='+this.cid+'&resize='+resize,
            secureuri:false,
            fileElementId:'fileToUpload_' + divid,
            dataType: 'json',
            success: function (data, status)
            {

                if(typeof(data.error) != 'undefined')
                {
                    if(data.error != '')
                    {

                        document.getElementById('gall_error').innerHTML  = data.error;
                    }else
                    {

                        if($("#imgall_"+divid).size()) {
                            $("#imgall_" + divid + "").remove();
                        }
                        document.getElementById('gall_' + divid).innerHTML  = data.msg;

                        if($('#ddgall_'+divid+'').size())
                        {
                            $("#ddgall_"+divid+"").remove();
                        }

                        if(isdescr==0) {
                            $('#gall_menu_' + divid + '').prepend('<div id="ddgall_' + divid + '" class="gall_cntr_menu"><ul id="gall_menu_item"><li class="photo_descript"  id="ad_' + divid + '" onclick="gl.AddDescription(' + divid + ');"></li><li class="photo_del"  id="dp_' + divid + '" onclick="gl.DelPhoto(' + divid + ');"></li></ul></div>');
                        }
                        else {
                            $('#gall_menu_' + divid + '').prepend('<div id="ddgall_' + divid + '" class="gall_cntr_menu"><ul id="gall_menu_item"><li class="photo_del"  id="dp_' + divid + '" onclick="gl.DelPhoto(' + divid + ');"></li></ul></div>');
                        }
                        //document.getElementById('UpInf_' + divid).innerHTML = data.msg;
                        //document.getElementById('newFile_' + divid).value =  data.html;
                        //document.getElementById('LogoUP_' + divid).innerHTML = '<div id="img_pict"><a  href="index.php?p=upan&sub=addlogo">'+data.msg+'</a></div>';

                    }
                }


            },
            error: function (data, status, e)
            {
                alert(data.toSource());
                document.getElementById('gall_error').innerHTML = e;
            }
        }
    );

    return false;

};

Gallery.prototype.AddMessage = function(num) {

    $.ajax({
        url:this.driver,
        method:"POST",
        data:{a:"AddMessage",pos:this.num,flatId:this.cid,dsc:$("#ph_descr").val()},
        success:function(data, status, jqXHR){
            eval(jqXHR.responseText);
        },
        complete:function(){

            if($("#ph_descr").val().length!=0)
            {
                $('#dgall_'+gl.num+'').remove();
                $('#gall_menu_'+gl.num+'').prepend('<div id="dgall_'+gl.num+'" class="gall_isinfo" onmouseover="gl.ShowInfo(this.id);" onmouseout="gl.HideInfo(this.id);" title="'+$("#ph_descr").val()+'"></div>');
            }
            else
            {
                $('#dgall_'+gl.num+'').remove();
            }
            $("#galldesr_dlg").dialog("close");

        }
    });

};
Gallery.prototype.ShowDescription = function(data) {

    this.descr_text=data.description;
};

Gallery.prototype.AddDescription = function(num)
{
    this.num=num;
    $.ajax({
        method:"POST",
        url:this.driver,
        data:{a:'showDescription',pid:num,flatId:this.cid},
        success:function(data, status, jqXHR){
            eval(jqXHR.responseText);
        },
        complete:function(){
            $("#galldesr_dlg").dialog("open");
            $("#ph_descr").val(gl.descr_text);
        }

    });

};

Gallery.prototype.DelPhoto=function(num){

    $.ajax({
        method:"POST",
        url:this.driver,
        data:{a:'DelPhoto',pos:num,flatId:this.cid},
        complete:function(){
            $("#imgall_"+num+"").remove();
            $('#dgall_'+num+'').remove();
            $("#ddgall_"+num+"").remove();

            this.sqlim=0;
        },
        success:function(data, status, jqXHR){
            eval(jqXHR.responseText);
        }
    });

};

Gallery.prototype.ShowPicture=function(id,photoName,containerId)
{
    $("#"+containerId+" img").fadeOut(500,function(){

        $("#"+containerId+" img").attr("src","/upload/flats/"+photoName);
        var c_w = $(".flat-picture-container").outerWidth();
        var i_w = $(".flat-picture-container img").outerWidth();

        var delta = Math.round((i_w-c_w)/2);

        if(delta>0)
        {
            $(".flat-picture-container img").css('left',(-delta)+"px");
        }
        else
        {
            $(".flat-picture-container img").css('left',"auto");
        }
    }).fadeIn(500);

};

