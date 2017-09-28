/**
 * Created by Nickolay Terekhin on 28.06.2015.
 */
var win = null;
function popup(url,title,w,h,scroll,lo,tb)
{
    leftPosition = (screen.width) ? (screen.width-w)/2 : 0;
    topPosition = (screen.height) ? (screen.height-h)/2 : 0;
    rez = lo || 1;
    settings =
        'height='+h+',width='+w+',top='+topPosition+',left='+leftPosition+',scrollbars='+scroll+',resizable='+rez+'';
    win = window.open(url,title,settings);
}

function cancelPopup()
{
        window.close();
}
