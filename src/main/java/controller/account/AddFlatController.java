package controller.account;

import com.sun.org.apache.xpath.internal.operations.Bool;
import controller.Controller;
import domain.Flat;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick on 04.04.2015.
 */
public class AddFlatController extends Controller {
    private Boolean isEdit = false;

    public AddFlatController(Boolean isEdit)
    {
        this.isEdit = isEdit;
    }
    @Override
    public void getModel(HttpServletRequest request, HttpServletResponse response, ServletContext ctx, TemplateEngine tmpl) throws Exception {
        if(isPermissions(request,"Users"))
        {
            WebContext ctxin = new WebContext(request,response,ctx,request.getLocale());
            if(request.getMethod().equals("POST") && Integer.valueOf(request.getParameter("send"))==1){
                if (flatValidation(request)) {

                    Flat flat = initFlatRequest(request);
                    if (isEdit)
                    {
                        db.getFlatJDBC().updateFlat(flat);
                        ctxin.setVariable("vmMessage","Flat Edited successfully!!!");
                    }
                    else {
                        db.getFlatJDBC().createFlat(flat);
                        ctxin.setVariable("vmMessage","Flat added successfully!!!");
                    }
                    ctxin.setVariable("done", 1);

                } else {
                    ctxin.setVariable("warn", true);
                    ctxin.setVariable("errors", errors);
                }
            }
            if(isEdit) {
                ctxin.setVariable("flat", db.getFlatJDBC().getFlatById(Long.parseLong(request.getParameter("flatId"))));
                ctxin.setVariable("isEdit", isEdit);
            }

            ctxin.setVariable("countries",db.getCountryJDBC().getCountries());
            tmpl.process("account/addflat",ctxin,response.getWriter());
        }
        else
        {

        }
    }
    private Flat initFlatRequest(HttpServletRequest request)
    {
        Flat flat = new Flat();
        if(isEdit) {
        flat.setFlatId(Long.parseLong(request.getParameter("flatId")));
        }
        flat.setUid(user.getUid());
        flat.setCountryId(Long.parseLong(request.getParameter("flatCountry")));
        flat.setCityName(request.getParameter("city"));
        flat.setDistrictName(request.getParameter("district"));
        flat.setStreet(request.getParameter("street"));
        flat.setFlatnumber(request.getParameter("flatNumber"));
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd.mm.yyyy");
        flat.setBuildDate(DateTime.parse(request.getParameter("buildDate"),dateTimeFormatter));
        flat.setRegDate(DateTime.now());
        flat.setLevel(Integer.parseInt(request.getParameter("flatLevel")));
        flat.setRoomsQty(Integer.parseInt(request.getParameter("flatRooms")));
        flat.setFlatSquare(Double.parseDouble(request.getParameter("flatSquare")));
        if(isRate(request.getParameter("isRent"))) {
            flat.setPriceForRent(Double.parseDouble(request.getParameter("priceForRent")));

            flat.setRentRate(request.getParameter("rentRate"));
            flat.setIsRent(true);
        }
        else
        {
            flat.setIsRent(true);
        }

        flat.setPriceForSale(Double.parseDouble(request.getParameter("priceForSale")));
        flat.setFullDescription(request.getParameter("flatDescript"));
        flat.setShortDescription("");
        flat.setIsActive(false);
        flat.setIsPrivate(isPrivate(request.getParameter("isPrivate")));
        flat.setDef_foto(true);
        flat.setPhoto("default_flat.jpg");
        return flat;
    }
    private boolean flatValidation(HttpServletRequest req)
    {
        errors = new ArrayList<>();
        String flatCountry = req.getParameter("flatCountry");
        String city = req.getParameter("city");
        String district = req.getParameter("district");
        String street = req.getParameter("street");
        String flatNumber = req.getParameter("flatNumber");
        String buildDate = req.getParameter("buildDate");
        String flatLevel = req.getParameter("flatLevel");
        String flatRooms = req.getParameter("flatRooms");
        String flatSquare = req.getParameter("flatSquare");
        String priceForRent = req.getParameter("priceForRent");
        String rentRate = req.getParameter("rentRate");
        String priceForSale = req.getParameter("priceForSale");
        String flatDescription = req.getParameter("flatDescript");
        String isRent = req.getParameter("isRent");

        if(flatCountry.isEmpty())
        {
            errors.add("Field: Country is required!!!");
        }
        if(city.isEmpty())
        {
            errors.add("Field: City is required!!!");
        }
        if(district.isEmpty())
        {
            errors.add("Field: District is Required");
        }
        if(street.isEmpty())
        {
            errors.add("Field: Street is required!!!");
        }
        if(flatNumber.isEmpty())
        {
            errors.add("Field: Flat Number is required!!!");
        }
        if(buildDate.isEmpty())
        {
            errors.add("Field: Build Date is required!!!");
        }
        else
        {
            try{
                DateTimeFormatter formatter =  DateTimeFormat.forPattern("d.M.y");
                formatter.parseDateTime(buildDate);
            } catch (IllegalArgumentException iae) {
                errors.add("Date Time format error!!!");
            }
        }
        if(flatLevel.isEmpty())
        {
            errors.add("Field: Flat Level is required!!!");
        }
        else
        {
            try {
                Integer.parseInt(flatLevel);
            }
            catch(NumberFormatException e)
            {
                errors.add("Field Level format error!!!");
            }
        }
        if(flatRooms.isEmpty())
        {
            errors.add("Field: Flat Rooms is required!!!");
        }
        else
        {
            try {
                Integer.parseInt(flatRooms);
            }
            catch(NumberFormatException e)
            {
                errors.add("Field Rooms format error!!!");
            }
        }
        if(flatSquare.isEmpty())
        {
            errors.add("Field: Flat Square Level is required!!!");
        }
        else
        {
            try {
                Double.parseDouble(flatSquare);
            }
            catch(NumberFormatException e)
            {
                errors.add("Field Flat Square format error!!!");
            }
        }
        if(!isRent.isEmpty() && Integer.parseInt(isRent)==1)
        {
            if (priceForRent.isEmpty()) {
                errors.add("Field: Price for Rent is required!!!");
            } else {
                try {
                    Double.parseDouble(priceForRent);
                } catch (NumberFormatException e) {
                    errors.add("Field 'Price for Rent' format error!!!");
                }
            }
            if (rentRate.isEmpty()) {
                errors.add("Field: Rent Rate is required!!!");
            }
        }

        if(priceForSale.isEmpty())
        {
            errors.add("Field: Flat Level is required!!!");
        }
        else
        {
            try {
                Double.parseDouble(priceForSale);
            }
            catch(NumberFormatException e)
            {
                errors.add("Field Price for Sale format error!!!");
            }
        }
        if(flatDescription.isEmpty())
        {
            errors.add("Field Flat Description is required");
        }
        if(errors.size()==0)
            return true;
        return false;
    }

    private Boolean isRate(String isRate)
    {
        if(isRate==null || isRate.isEmpty())
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    private Boolean isPrivate(String isPrivate)
    {
        if(isPrivate==null || isPrivate.isEmpty())
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}
