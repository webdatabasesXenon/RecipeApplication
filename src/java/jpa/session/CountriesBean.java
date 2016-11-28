/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.session;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Named;

/**
 *
 * @author Greg
 */
@Named(value = "CountriesBean")
@Startup
@Singleton
public class CountriesBean {
    
    private Map<String, Object> countries = new LinkedHashMap<String,Object>();

    @PostConstruct
    void init() {
        countries.put("United States","US");
        countries.put("Canada","CA");
        countries.put("Afghanistan","AF");
        countries.put("Åland Islands","AX");
        countries.put("Albania","AL");
        countries.put("Algeria","DZ");
        countries.put("American Samoa","AS");
        countries.put("Andorra","AD");
        countries.put("Angola","AO");
        countries.put("Anguilla","AI");
        countries.put("Antarctica","AQ");
        countries.put("Antigua and Barbuda","AG");
        countries.put("Argentina","AR");
        countries.put("Armenia","AM");
        countries.put("Aruba","AW");
        countries.put("Australia","AU");
        countries.put("Austria","AT");
        countries.put("Azerbaijan","AZ");
        countries.put("Bahamas","BS");
        countries.put("Bahrain","BH");
        countries.put("Bangladesh","BD");
        countries.put("Barbados","BB");
        countries.put("Belarus","BY");
        countries.put("Belgium","BE");
        countries.put("Belize","BZ");
        countries.put("Benin","BJ");
        countries.put("Bermuda","BM");
        countries.put("Bhutan","BT");
        countries.put("Bolivia, Plurinational State of","BO");
        countries.put("Bosnia and Herzegovina","BA");
        countries.put("Botswana","BW");
        countries.put("Bouvet Island","BV");
        countries.put("Brazil","BR");
        countries.put("British Indian Ocean Territory","IO");
        countries.put("Brunei Darussalam","BN");
        countries.put("Bulgaria","BG");
        countries.put("Burkina Faso","BF");
        countries.put("Burundi","BI");
        countries.put("Cambodia","KH");
        countries.put("Cameroon","CM");
        countries.put("Cape Verde","CV");
        countries.put("Cayman Islands","KY");
        countries.put("Central African Republic","CF");
        countries.put("Chad","TD");
        countries.put("Chile","CL");
        countries.put("China","CN");
        countries.put("Christmas Island","CX");
        countries.put("Cocos (Keeling) Islands","CC");
        countries.put("Colombia","CO");
        countries.put("Comoros","KM");
        countries.put("Congo","CG");
        countries.put("Cook Islands","CK");
        countries.put("Costa Rica","CR");
        countries.put("Côte d'Ivoire","CI");
        countries.put("Croatia","HR");
        countries.put("Cuba","CU");
        countries.put("Curaçao","CW");
        countries.put("Cyprus","CY");
        countries.put("Czech Republic","CZ");
        countries.put("Denmark","DK");
        countries.put("Djibouti","DJ");
        countries.put("Dominica","DM");
        countries.put("Dominican Republic","DO");
        countries.put("Ecuador","EC");
        countries.put("Egypt","EG");
        countries.put("El Salvador","SV");
        countries.put("Equatorial Guinea","GQ");
        countries.put("Eritrea","ER");
        countries.put("Estonia","EE");
        countries.put("Ethiopia","ET");
        countries.put("Falkland Islands (Malvinas)","FK");
        countries.put("Faroe Islands","FO");
        countries.put("Fiji","FJ");
        countries.put("Finland","FI");
        countries.put("France","FR");
        countries.put("French Guiana","GF");
        countries.put("French Polynesia","PF");
        countries.put("French Southern Territories","TF");
        countries.put("Gabon","GA");
        countries.put("Gambia","GM");
        countries.put("Georgia","GE");
        countries.put("Germany","DE");
        countries.put("Ghana","GH");
        countries.put("Gibraltar","GI");
        countries.put("Greece","GR");
        countries.put("Greenland","GL");
        countries.put("Grenada","GD");
        countries.put("Guadeloupe","GP");
        countries.put("Guam","GU");
        countries.put("Guatemala","GT");
        countries.put("Guernsey","GG");
        countries.put("Guinea","GN");
        countries.put("Guinea-Bissau","GW");
        countries.put("Guyana","GY");
        countries.put("Haiti","HT");
        countries.put("Heard Island and McDonald Islands","HM");
        countries.put("Holy See (Vatican City State)","VA");
        countries.put("Honduras","HN");
        countries.put("Hong Kong","HK");
        countries.put("Hungary","HU");
        countries.put("Iceland","IS");
        countries.put("India","IN");
        countries.put("Indonesia","ID");
        countries.put("Iran","IR");
        countries.put("Iraq","IQ");
        countries.put("Ireland","IE");
        countries.put("Isle of Man","IM");
        countries.put("Israel","IL");
        countries.put("Italy","IT");
        countries.put("Jamaica","JM");
        countries.put("Japan","JP");
        countries.put("Jersey","JE");
        countries.put("Jordan","JO");
        countries.put("Kazakhstan","KZ");
        countries.put("Kenya","KE");
        countries.put("Kiribati","KI");
        countries.put("Korea (Democratic Republic)", "KP");
        countries.put("Korea, Republic of", "KR");
        countries.put("Kuwait","KW");
        countries.put("Kyrgyzstan","KG");
        countries.put("Lao People's Democratic Republic","LA");
        countries.put("Latvia","LV");
        countries.put("Lebanon","LB");
        countries.put("Lesotho","LS");
        countries.put("Liberia","LR");
        countries.put("Libya","LY");
        countries.put("Liechtenstein","LI");
        countries.put("Lithuania","LT");
        countries.put("Luxembourg","LU");
        countries.put("Macao","MO");
        countries.put("Macedonia", "MK");
        countries.put("Madagascar","MG");
        countries.put("Malawi","MW");
        countries.put("Malaysia","MY");
        countries.put("Maldives","MV");
        countries.put("Mali","ML");
        countries.put("Malta","MT");
        countries.put("Marshall Islands","MH");
        countries.put("Martinique","MQ");
        countries.put("Mauritania","MR");
        countries.put("Mauritius","MU");
        countries.put("Mayotte","YT");
        countries.put("Mexico","MX");
        countries.put("Micronesia","");
        countries.put("Moldova,Republic of", "MD");
        countries.put("Monaco","MC");
        countries.put("Mongolia","MN");
        countries.put("Montenegro","ME");
        countries.put("Montserrat","MS");
        countries.put("Morocco","MA");
        countries.put("Mozambique","MZ");
        countries.put("Myanmar","MM");
        countries.put("Namibia","NA");
        countries.put("Nauru","NR");
        countries.put("Nepal","NP");
        countries.put("Netherlands","NL");
        countries.put("New Caledonia","NC");
        countries.put("New Zealand","NZ");
        countries.put("Nicaragua","NI");
        countries.put("Niger","NE");
        countries.put("Nigeria","NG");
        countries.put("Niue","NU");
        countries.put("Norfolk Island","NF");
        countries.put("Northern Mariana Islands","MP");
        countries.put("Norway","NO");
        countries.put("Oman","OM");
        countries.put("Pakistan","PK");
        countries.put("Palau","PW");
        countries.put("Palestine, State of", "PS");
        countries.put("Panama","PA");
        countries.put("Papua New Guinea","PG");
        countries.put("Paraguay","PY");
        countries.put("Peru","PE");
        countries.put("Philippines","PH");
        countries.put("Pitcairn","PN");
        countries.put("Poland","PL");
        countries.put("Portugal","PT");
        countries.put("Puerto Rico","PR");
        countries.put("Qatar","QA");
        countries.put("Réunion","RE");
        countries.put("Romania","RO");
        countries.put("Russian Federation","RU");
        countries.put("Rwanda","RW");
        countries.put("Saint Barthélemy","BL");
        countries.put("Saint Helena, Ascension and Tristan da Cunha","SH");
        countries.put("Saint Kitts and Nevis","KN");
        countries.put("Saint Lucia","LC");
        countries.put("Saint Martin (French part)","MF");
        countries.put("Saint Pierre and Miquelon","PM");
        countries.put("Saint Vincent and the Grenadines","VC");
        countries.put("Samoa","WS");
        countries.put("San Marino","SM");
        countries.put("Sao Tome and Principe","ST");
        countries.put("Saudi Arabia","SA");
        countries.put("Senegal","SN");
        countries.put("Serbia","RS");
        countries.put("Seychelles","SC");
        countries.put("Sierra Leone","SL");
        countries.put("Singapore","SG");
        countries.put("Sint Maarten (Dutch part)","SX");
        countries.put("Slovakia","SK");
        countries.put("Slovenia","SI");
        countries.put("Solomon Islands","SB");
        countries.put("Somalia","SO");
        countries.put("South Africa","ZA");
        countries.put("South Georgia and the South Sandwich Islands","GS");
        countries.put("South Sudan","SS");
        countries.put("Spain","ES");
        countries.put("Sri Lanka","LK");
        countries.put("Sudan","SD");
        countries.put("Suriname","SR");
        countries.put("Svalbard and Jan Mayen","SJ");
        countries.put("Swaziland","SZ");
        countries.put("Sweden","SE");
        countries.put("Switzerland","CH");
        countries.put("Syrian Arab Republic","SY");
        countries.put("Taiwan, Province of China", "TW");
        countries.put("Tajikistan","TJ");
        countries.put("Tanzania,United Republic of", "TZ");
        countries.put("Thailand","TH");
        countries.put("Timor-Leste","TL");
        countries.put("Togo","TG");
        countries.put("Tokelau","TK");
        countries.put("Tonga","TO");
        countries.put("Trinidad and Tobago","TT");
        countries.put("Tunisia","TN");
        countries.put("Turkey","TR");
        countries.put("Turkmenistan","TM");
        countries.put("Turks and Caicos Islands","TC");
        countries.put("Tuvalu","TV");
        countries.put("Uganda","UG");
        countries.put("Ukraine","UA");
        countries.put("United Arab Emirates","AE");
        countries.put("United Kingdom","GB");
        countries.put("United States Minor Outlying Islands","UM");
        countries.put("Uruguay","UY");
        countries.put("Uzbekistan","UZ");
        countries.put("Vanuatu","VU");
        countries.put("Venezuela,Bolivarian Republic of", "VE");
        countries.put("Viet Nam","VN");
        countries.put("Virgin Islands, British", "VG");
        countries.put("Virgin Islands, U.S.", "VI");
        countries.put("Wallis and Futuna","WF");
        countries.put("Western Sahara","EH");
        countries.put("Yemen","YE");
        countries.put("Zambia","ZM");
        countries.put("Zimbabwe","ZW");
    }

    public Map<String, Object> getCountries() {
        return countries;
    }

    public void setCountries(Map<String, Object> countries) {
        this.countries = countries;
    }
    
    
}
