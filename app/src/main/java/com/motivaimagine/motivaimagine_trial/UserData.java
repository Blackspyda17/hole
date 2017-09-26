package com.motivaimagine.motivaimagine_trial;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class UserData extends Fragment {
    public String CADENA_PAISES="[ { \"id\": 1, \"name\": \"Vatican City\" }, { \"id\": 2, \"name\": \"Switzerland\" }, " +
            "{ \"id\": 3, \"name\": \"Andorra\" }, { \"id\": 4, \"name\": \"Estonia\" }, { \"id\": 5, \"name\": \"Iceland\" }, " +
            "{ \"id\": 6, \"name\": \"Armenia\" }, { \"id\": 7, \"name\": \"Albania\" }, { \"id\": 8, \"name\": \"Czech Republic\" }," +
            " { \"id\": 9, \"name\": \"Georgia\" }, { \"id\": 10, \"name\": \"Austria\" }, { \"id\": 11, \"name\": \"Ireland\" }, { \"id\": 12, " +
            "\"name\": \"Gibraltar\" }, { \"id\": 13, \"name\": \"Greece\" }, { \"id\": 14, \"name\": \"Netherlands\" }, { \"id\": 15, \"name\": \"Portugal\" }" +
            ", { \"id\": 16, \"name\": \"Norway\" }, { \"id\": 17, \"name\": \"Latvia\" }, { \"id\": 18, \"name\": \"Lithuania\" }, { \"id\": 19, \"name\": \"Luxembourg\" }," +
            " { \"id\": 20, \"name\": \"Spain\" }, { \"id\": 21, \"name\": \"Italy\" }, { \"id\": 22, \"name\": \"Romania\" }, { \"id\": 23, \"name\": \"Poland\" }," +
            " { \"id\": 24, \"name\": \"Belgium\" }, { \"id\": 25, \"name\": \"France\" }, { \"id\": 26, \"name\": \"Bulgaria\" }, { \"id\": 27, \"name\": \"Denmark\" }," +
            " { \"id\": 28, \"name\": \"Croatia\" }, { \"id\": 29, \"name\": \"Germany\" }, { \"id\": 30, \"name\": \"Hungary\" }, { \"id\": 31, \"name\": \"Bosnia/herzegovina\" }," +
            " { \"id\": 32, \"name\": \"Finland\" }, { \"id\": 33, \"name\": \"Belarus\" }, { \"id\": 34, \"name\": \"Faeroe Islands\" }, { \"id\": 35, \"name\": \"Monaco\" }, " +
            "{ \"id\": 36, \"name\": \"Cyprus\" }, { \"id\": 37, \"name\": \"Macedonia\" }, { \"id\": 38, \"name\": \"Slovakia\" }, { \"id\": 39, \"name\": \"Malta\" }, " +
            "{ \"id\": 40, \"name\": \"Slovenia\" }, { \"id\": 41, \"name\": \"San Marino\" }, { \"id\": 42, \"name\": \"Sweden\" }, { \"id\": 43, \"name\": \"United Kingdom\" }," +
            " { \"id\": 44, \"name\": \"Cook Islands\" }, { \"id\": 45, \"name\": \"Palau\" }, { \"id\": 46, \"name\": \"Tuvalu\" }, { \"id\": 47, \"name\": \"Nauru\" }, " +
            "{ \"id\": 48, \"name\": \"Kiribati\" }, { \"id\": 49, \"name\": \"Marshall Islands\" }, { \"id\": 50, \"name\": \"Niue\" }, { \"id\": 51, \"name\": \"Tonga\" }," +
            " { \"id\": 52, \"name\": \"New Zealand\" }, { \"id\": 53, \"name\": \"Australia\" }, { \"id\": 54, \"name\": \"Vanuatu\" }, { \"id\": 55, \"name\": \"Solomon Islands\" }" +
            ", { \"id\": 56, \"name\": \"Samoa\" }, { \"id\": 57, \"name\": \"Fiji\" }, { \"id\": 58, \"name\": \"Micronesia\" }, { \"id\": 59, \"name\": \"Guinea-bissau\" }, " +
            "{ \"id\": 60, \"name\": \"Zambia\" }, { \"id\": 61, \"name\": \"Ivory Coast\" }, { \"id\": 62, \"name\": \"Western Sahara\" }, { \"id\": 63, \"name\": \"Equatorial Guinea\" }, " +
            "{ \"id\": 64, \"name\": \"Egypt\" }, { \"id\": 65, \"name\": \"Congo\" }, { \"id\": 66, \"name\": \"Central African Republic\" }, { \"id\": 67, \"name\": \"Angola\" }," +
            " { \"id\": 68, \"name\": \"Gabon\" }, { \"id\": 69, \"name\": \"Ethiopia\" }, { \"id\": 70, \"name\": \"Guinea\" }, { \"id\": 71, \"name\": \"Gambia\" }, " +
            "{ \"id\": 72, \"name\": \"Zimbabwe\" }, { \"id\": 73, \"name\": \"Cape Verde\" }, { \"id\": 74, \"name\": \"Ghana\" }, { \"id\": 75, \"name\": \"Rwanda\" }, " +
            "{ \"id\": 76, \"name\": \"Tanzania\" }, { \"id\": 77, \"name\": \"Cameroon\" }, { \"id\": 78, \"name\": \"Namibia\" }, { \"id\": 79, \"name\": \"Niger\" }, " +
            "{ \"id\": 80, \"name\": \"Nigeria\" }, { \"id\": 81, \"name\": \"Tunisia\" }, { \"id\": 82, \"name\": \"Liberia\" }, { \"id\": 83, \"name\": \"Lesotho\" }, " +
            "{ \"id\": 84, \"name\": \"Togo\" }, { \"id\": 85, \"name\": \"Chad\" }, { \"id\": 86, \"name\": \"Eritrea\" }, { \"id\": 87, \"name\": \"Libya\" }, " +
            "{ \"id\": 88, \"name\": \"Burkina Faso\" }, { \"id\": 89, \"name\": \"Djibouti\" }, { \"id\": 90, \"name\": \"Sierra Leone\" }, { \"id\": 91, \"name\": \"Burundi\" }, " +
            "{ \"id\": 92, \"name\": \"Benin\" }, { \"id\": 93, \"name\": \"South Africa\" }, { \"id\": 94, \"name\": \"Botswana\" }, { \"id\": 95, \"name\": \"Algeria\" }, " +
            "{ \"id\": 96, \"name\": \"Swaziland\" }, { \"id\": 97, \"name\": \"Madagascar\" }, { \"id\": 98, \"name\": \"Morocco\" }, { \"id\": 99, \"name\": \"Kenya\" }, " +
            "{ \"id\": 100, \"name\": \"Mali\" }, { \"id\": 101, \"name\": \"Comoros\" }, { \"id\": 102, \"name\": \"Sao Tome And Principe\" }, { \"id\": 103, \"name\": \"Mauritius\" }," +
            " { \"id\": 104, \"name\": \"Malawi\" }, { \"id\": 105, \"name\": \"Somalia\" }, { \"id\": 106, \"name\": \"Senegal\" }, { \"id\": 107, \"name\": \"Mauritania\" }, " +
            "{ \"id\": 108, \"name\": \"Seychelles\" }, { \"id\": 109, \"name\": \"Uganda\" }, { \"id\": 110, \"name\": \"Sudan\" }, { \"id\": 111, \"name\": \"Mozambique\" }," +
            " { \"id\": 112, \"name\": \"Mongolia\" }, { \"id\": 113, \"name\": \"China\" }, { \"id\": 114, \"name\": \"Afghanistan\" }, { \"id\": 115, \"name\": \"Serbia\" }," +
            " { \"id\": 116, \"name\": \"Vietnam\" }, { \"id\": 117, \"name\": \"Canary Islands\" }, { \"id\": 118, \"name\": \"India\" }, { \"id\": 119, \"name\": \"Azerbaijan\" }, " +
            "{ \"id\": 120, \"name\": \"Indonesia\" }, { \"id\": 121, \"name\": \"Russia\" }, { \"id\": 122, \"name\": \"Laos\" }, { \"id\": 123, \"name\": \"Taiwan\" }, " +
            "{ \"id\": 124, \"name\": \"Turkey\" }, { \"id\": 125, \"name\": \"Sri Lanka\" }, { \"id\": 126, \"name\": \"Turkmenistan\" }, { \"id\": 127, \"name\": \"Tajikistan\" }," +
            " { \"id\": 128, \"name\": \"Papua New Guinea\" }, { \"id\": 129, \"name\": \"Thailand\" }, { \"id\": 130, \"name\": \"Nepal\" }, { \"id\": 131, \"name\": \"Pakistan\" }, " +
            "{ \"id\": 132, \"name\": \"Philippines\" }, { \"id\": 133, \"name\": \"Bangladesh\" }, { \"id\": 134, \"name\": \"Ukraine\" }, { \"id\": 135, \"name\": \"Brunei\" }, " +
            "{ \"id\": 136, \"name\": \"Japan\" }, { \"id\": 137, \"name\": \"Bhutan\" }, { \"id\": 138, \"name\": \"Hong Kong\" }, { \"id\": 139, \"name\": \"Kyrgyzstan\" }, " +
            "{ \"id\": 140, \"name\": \"Uzbekistan\" }, { \"id\": 141, \"name\": \"Burma (myanmar)\" }, { \"id\": 142, \"name\": \"Singapore\" }, { \"id\": 143, \"name\": \"Macau\" }," +
            " { \"id\": 144, \"name\": \"Cambodia\" }, { \"id\": 145, \"name\": \"Republic of Korea \" }, { \"id\": 146, \"name\": \"Maldives\" }, { \"id\": 147, \"name\": \"Kazakhstan\" }, " +
            "{ \"id\": 148, \"name\": \"Malaysia\" }, { \"id\": 149, \"name\": \"Guatemala\" }, { \"id\": 150, \"name\": \"Antigua And Barbuda\" }, { \"id\": 151, \"name\": \"British Virgin Islands (uk)\" }," +
            " { \"id\": 152, \"name\": \"Anguilla (uk)\" }, { \"id\": 153, \"name\": \"Virgin Island\" }, { \"id\": 154, \"name\": \"Canada\" }, { \"id\": 155, \"name\": \"Grenada\" }, { \"id\": 156, \"name\": \"Aruba (netherlands)\" }," +
            " { \"id\": 157, \"name\": \"Costa Rica\" }, { \"id\": 158, \"name\": \"Cuba\" }, { \"id\": 159, \"name\": \"Puerto Rico (us)\" }, { \"id\": 160, \"name\": \"Nicaragua\" }, { \"id\": 161, \"name\": \"Trinidad And Tobago\" }, " +
            "{ \"id\": 162, \"name\": \"Guadeloupe (france)\" }, { \"id\": 163, \"name\": \"Panama\" }, { \"id\": 164, \"name\": \"Dominican Republic\" }, { \"id\": 165, \"name\": \"Dominica\" }, { \"id\": 166, \"name\": \"Barbados\" }," +
            " { \"id\": 167, \"name\": \"Haiti\" }, { \"id\": 168, \"name\": \"Jamaica\" }, { \"id\": 169, \"name\": \"Honduras\" }, { \"id\": 170, \"name\": \"Bahamas, The\" }, { \"id\": 171, \"name\": \"Belize\" }, { \"id\": 172, \"name\": \"Saint Kitts And Nevis\" }," +
            " { \"id\": 173, \"name\": \"El Salvador\" }, { \"id\": 174, \"name\": \"United States\" }, { \"id\": 175, \"name\": \"Martinique (france)\" }, { \"id\": 176, \"name\": \"Monsterrat (uk)\" }, { \"id\": 177, \"name\": \"Cayman Islands (uk)\" }, " +
            "{ \"id\": 178, \"name\": \"Mexico\" }, { \"id\": 179, \"name\": \"South Georgia and the South Sandwich Islands\" }, { \"id\": 180, \"name\": \"Paraguay\" }, { \"id\": 181, \"name\": \"Colombia\" }, { \"id\": 182, \"name\": \"Venezuela\" }, " +
            "{ \"id\": 183, \"name\": \"Chile\" }, { \"id\": 184, \"name\": \"Suriname\" }, { \"id\": 185, \"name\": \"Bolivia\" }, { \"id\": 186, \"name\": \"Ecuador\" }, { \"id\": 187, \"name\": \"French Guiana\" }, { \"id\": 188, \"name\": \"Argentina\" }, " +
            "{ \"id\": 189, \"name\": \"Guyana\" }, { \"id\": 190, \"name\": \"Brazil\" }, { \"id\": 191, \"name\": \"Peru\" }, { \"id\": 192, \"name\": \"Uruguay\" }, { \"id\": 193, \"name\": \"Falkland Islands\" }, { \"id\": 194, \"name\": \"Oman\" }," +
            " { \"id\": 195, \"name\": \"Lebanon\" }, { \"id\": 196, \"name\": \"Iraq\" }, { \"id\": 197, \"name\": \"Yemen\" }, { \"id\": 198, \"name\": \"Iran\" }, { \"id\": 199, \"name\": \"Bahrain\" }, { \"id\": 200, \"name\": \"Syria\" }," +
            " { \"id\": 201, \"name\": \"Qatar\" }, { \"id\": 202, \"name\": \"Jordan\" }, { \"id\": 203, \"name\": \"Kuwait\" }, { \"id\": 204, \"name\": \"Israel\" }, { \"id\": 205, \"name\": \"United Arab Emirates\" }, { \"id\": 206, \"name\": \"Saudi Arabia\" }, " +
            "{ \"id\": 207, \"name\": \"Liechtenstein\" }, { \"id\": 208, \"name\": \"Democratic People's Republic of Korea\" }, { \"id\": 209, \"name\": \"Montenegro\" } ]";
    public static final int DATEPICKER_FRAGMENT = 1;
    private int sMonth, sYear, sDay;
    @BindView(R.id.edt_date) TextView _date;
    @BindView(R.id.ln_date) LinearLayout _campo_date;
    @BindView(R.id.sp_country) Spinner _country;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     View v= inflater.inflate(R.layout.fragment_user_data, container, false);
        ButterKnife.bind(this, v);


        _date.setText("" + FechaAct());


        _campo_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment picker = SelectDateFragment.newInstance(1);
                picker.setTargetFragment(UserData.this, DATEPICKER_FRAGMENT);
                picker.show(getFragmentManager().beginTransaction(), "Date Picker");


            }
        });



        Type listType = new TypeToken<ArrayList<Country>>(){}.getType();
        String json = CADENA_PAISES;
        Gson gson = new Gson();
        List<Country> paises = gson.fromJson(CADENA_PAISES, listType);
        List<String> countries = new ArrayList<String>();
        for (int i = 0; i < paises.size(); i++) {
            countries.add(paises.get(i).getName());
        }
        Collections.sort(countries);


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, countries);


        // Drop down layout style - list view with radio button
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        _country.setAdapter(dataAdapter2);

        return v;
    }

    private String FechaAct() {

          int MILLIS_IN_SECOND = 1000;
        int SECONDS_IN_MINUTE = 60;
         int MINUTES_IN_HOUR = 60;
         int HOURS_IN_DAY = 24;
         int DAYS_IN_YEAR = 365;
         long MILLISECONDS_IN_YEAR = (long) MILLIS_IN_SECOND * SECONDS_IN_MINUTE * MINUTES_IN_HOUR * HOURS_IN_DAY * DAYS_IN_YEAR;
        String dia = "";
        String mes = "";
        Calendar C = Calendar.getInstance();

        C.setTimeInMillis(System.currentTimeMillis() - (MILLISECONDS_IN_YEAR * 18));
        sYear = C.get(Calendar.YEAR);
        sMonth = C.get(Calendar.MONTH);
        sDay = C.get(Calendar.DAY_OF_MONTH);



        if (sDay < 10) {
            dia = "0" + sDay;
        } else {
            dia = "" + sDay;
        }

        if (sMonth < 10) {
            mes = "0" + sMonth;
        } else {
            mes = "" + sMonth;
        }
        return dia + "/" + mes + "/" + sYear;
    }


}
