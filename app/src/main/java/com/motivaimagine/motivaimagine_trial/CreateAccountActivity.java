package com.motivaimagine.motivaimagine_trial;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Scroller;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.motivaimagine.motivaimagine_trial.facebook.FacebookHelper;
import com.motivaimagine.motivaimagine_trial.facebook.FacebookListener;
import com.motivaimagine.motivaimagine_trial.google.GoogleHelper;
import com.motivaimagine.motivaimagine_trial.google.GoogleListener;
import com.motivaimagine.motivaimagine_trial.rest_client.user.Controller;
import com.motivaimagine.motivaimagine_trial.rest_client.user.listeners.LoginListener;
import com.motivaimagine.motivaimagine_trial.rest_client.user.models.Error;
import com.motivaimagine.motivaimagine_trial.rest_client.user.models.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A login screen that offers login via email/password.
 */
public class CreateAccountActivity extends AppCompatActivity implements FacebookListener,GoogleListener {

    private int Doctor_id;
    public ProgressDialog progressDialog;
    private String METHOD = "E";
    private String TOKEN=null;
    private String PHOTO=null;
    public DB mydb;
    public  FacebookHelper mFacebook;
    public  GoogleHelper mGoogle;
    boolean resultado=false;
    List<Country> paises;
    private GoogleApiClient googleApiClient;
    public String TERMS = "Terms and Conditions\n" +
            "Introduction\n" +
            "\n" +
            "Welcome to www.motivaomplants.com (the “Site”). Establishment Labs Holdings Inc (referred to as “Establishment Labs”, “we”, “us” or “our”, as applicable) provides you with access to the Site subject to the terms and conditions contained in this Terms of Use Agreement (the “Agreement’). Please read this Agreement carefully. By accessing or using this Site, you agree without restriction to be bound by this Agreement. If you do not agree to follow and be bound by this Agreement, you may not access, use or download materials from this Site.\n" +
            "\n" +
            "\n" +
            "Please note that if you are an existing customer or supplier of ESTABLISHMENT LABS, these terms are not intended to replace or modify those contained in your customer or vendor agreement. In case of an inconsistency, the terms and conditions of your written agreement will prevail over those contained herein. All ESTABLISHMENT LABS products and services may also be subject to separate terms and conditions which govern their use.\n" +
            "\n" +
            "ESTABLISHMENT LABS reserves the right to update or modify this Agreement at any time without prior notice. Your use of this Site following any such change constitutes your agreement to follow and be bound by this Agreement as revised. For this reason, we encourage you to review this Agreement each time you use this Site. This Agreement was last revised on June 20, 2017.\n" +
            "\n" +
            "The Availability of the Site and International Users\n" +
            "The Site is administered by ESTABLISHMENT LABS Holdings Inc, and its contents (the “Content”) is designed to comply with local applicable laws and regulations. Access to the Site and the Content may not be legal by certain persons or in certain countries. If you access this Site from outside the United States, you do so at your own risk and are responsible for compliance with the laws of your jurisdiction. Materials published on this Site may refer to products or services that are not available in your country. Consult your local ESTABLISHMENT LABS office for more information. Furthermore, ESTABLISHMENT LABS makes no representation that the materials on the Site are appropriate or available for use at other locations outside of the United States and access to them from countries where their contents are illegal is prohibited. You should not construe anything on the Site as a promotion or solicitation for any product or for the use of any product that is not authorized by the laws and regulations of the country in which you are located.\n" +
            "\n" +
            "This Site is Not a Source of Medical Advice\n" +
            "The Content of this Site is presented in summary form, is general in nature, and is provided for informational purposes only; it is not intended nor recommended as a substitute for professional medical advice. You should not use the Content of this Site for diagnosing a health or fitness problem, disease or recommendation. Always seek the advice of your physician or other qualified health provider regarding any medical condition or treatment. Nothing contained on this Site is intended to be for medical advise. Never disregard medical advice or delay in seeking it because of something you have read on this Site.\n" +
            "\n" +
            "Copyright Notice and Limitation on Use\n" +
            "No use should be made of materials on this Site, except as expressly authorized by this Agreement. All Site Content, including the selection, arrangement and design of the Content is owned either by ESTABLISHMENT LABS or its licensors and is protected by copyright and other intellectual property laws including the sui generis rights relating to the protection of databases. You may not modify, copy, reproduce, republish, upload, post, transmit or distribute in any way any Content, in whole or in part, including any code and software. You may download Content from the Site for your own personal, non-commercial use only, provided the Content is not modified in any way you keep intact all copyright and other proprietary notices and you include the phrase, “Used with permission of Establishment Labs” when you display or otherwise use the Content.\n" +
            "\n" +
            "Procedures for Claimed Copyright Infringement\n" +
            "We respect the intellectual property rights of others and we ask that you do the same. If you believe that your work has been copied in a way that constitutes copyright infringement, please provide our Legal and Compliance Team, Email: legalaffairs@establishmentlabs.com with the following information:\n" +
            "•an electronic or physical signature of the person authorized to act on behalf or the owner of the copyright interest;\n" +
            "•a description of the copyrighted work that you claim has been infringed;\n" +
            "•a description of where the material that you claim is infringing is located on the Site;\n" +
            "•your address, telephone number and email address;\n" +
            "•a statement by you that you have a good faith belief that the disputed use is not authorized by the copyright owner, its agent, or the law;\n" +
            "•a statement by you, made under penalty of perjury, that the above information in your notice is accurate and that you are the copyright owner or duly authorized to act on the copyright owner’s behalf.\n" +
            "\n" +
            "Trademark Notice\n" +
            "All of the trademarks, service marks and logos displayed on this Site (the “Trademark(s)”) are registered and unregistered trademarks of ESTABLISHMENT LABS, its affiliates, or third parties. Nothing contained in this Site should be construed as granting, by implication, estoppel, or otherwise, any license or right in and to the Trademarks without the express written permission of ESTABLISHMENT LABS or the applicable third party. Except as expressly provided in this Agreement, any use of the Trademarks found on the Site is strictly prohibited. Please note that the names of the companies and products mentioned on this site may be trademarks of their respective owners. A complete list of ESTABLISHMENT LABS’s trademarks for each operating segment is provided below.\n" +
            "\n" +
            "ESTABLISHMENT LABS Brands\n" +
            "\n" +
            "Public and Unsolicited Information\n" +
            "This Site may provide opportunities to provide ESTABLISHMENT LABS feedback regarding this Site and our products and other unsolicited submissions (collectively, “Unsolicited Information”). You may only provide Unsolicited Information which meets the requirements of these Terms and Conditions.\n" +
            "ESTABLISHMENT LABS and its employees do not accept or consider other unsolicited ideas, including ideas for new advertising campaigns, new promotions, new products or technologies, processes, materials, marketing plans or new product names. Please do not send any original creative artwork, samples, demos, or other works. The sole purpose of this policy is to avoid potential misunderstandings or disputes when ESTABLISHMENT LABS’s products or marketing strategies might seem similar to ideas submitted to ESTABLISHMENT LABS. So, except under the circumstances described above relating to patented ideas or those for which you have filed a patent application, please do not send your unsolicited ideas to ESTABLISHMENT LABS or anyone at ESTABLISHMENT LABS.\n" +
            "If, despite our request that you not send us your ideas and materials, you still send them, and in the event you post Public Information on our Site, all such Public Information and all such Unsolicited Information will be considered NON-CONFIDENTIAL and NON-PROPRIETARY and ESTABLISHMENT LABS and its affiliates are free to use such information for any purpose and in any manner whatsoever.\n" +
            "This Site may present opportunities to post information to public areas of the Site, such as bulletin boards (collectively, “Public Areas”), or to send along comments and feedback (any such information provided, “Public Information”). Although ESTABLISHMENT LABS may, from time to time, monitor or review the information posted to the Public Areas of the Site, ESTABLISHMENT LABS is under no obligation to do so, and assumes no responsibility or liability arising from the Content of such Public Areas or the Site in general or for any error, defamation, libel, slander, omission, falsehood, obscenity, pornography, profanity, danger, or inaccuracy contained in the Site.\n" +
            "You are prohibited from posting or transmitting any unlawful, threatening, libelous, defamatory, obscene, inflammatory, pornographic, or profane material or any material that could constitute or encourage conduct that would be considered a criminal offense, give rise to civil liability, or otherwise violate the law. ESTABLISHMENT LABS will fully cooperate with any law enforcement authorities or court order requesting or directing ESTABLISHMENT LABS to disclose the identity of anyone posting any such material.\n" +
            "Children under the age of eighteen should not submit any Public Information or Unsolicited Information containing personally identifiable data.\n" +
            "\n" +
            "Links to Other Websites\n" +
            "This Agreement applies only to this Site. This Site may frame or contain references or links to other ESTABLISHMENT LABS Web sites (the “Other ESTABLISHMENT LABS Sites”).\n" +
            "Please note that other ESTABLISHMENT LABS Sites are governed by their specific Terms and Conditions and Privacy Policies. We recommend that you carefully read those documents upon your entry to those other ESTABLISHMENT LABS Sites.\n" +
            "\n" +
            "No Framing Allowed\n" +
            "Elements of this Site are protected by copyright, trade dress, trademark, unfair competition, and other laws and may not be copied or imitated in whole or in part by any means, including but not limited to, the use of framing, deep linking or mirrors. None of the Content for our Site may be retransmitted without the express written consent of ESTABLISHMENT LABS. If you are interested in linking to our Site, Contact Us for more information.\n" +
            "\n" +
            "Dealings with Advertisers\n" +
            "Your correspondence or business dealings with, or participation in promotions of, advertisers found on or through the Site, including payment and delivery of related goods or services, and any other terms, conditions, warranties or representations associated with such dealings are solely between you and such advertiser. You agree that we are not responsible nor shall we be liable for loss or damage of any sort incurred as a result of any such dealings or as the result of the presence of such advertiser on the Site.\n" +
            "\n" +
            "Account Registration\n" +
            "Some of the functions of this Site may require creation of an account with us. As part of the registration process, visitors will select a Doctor_id Name and Password, along with registration information, which must be accurate and updated. You may not select or use a Doctor_id Name of another person with the intent to impersonate that person or use a Doctor_id Name in which another person has rights without such person’s authorization. Failure to comply with the above shall constitute a breach of this Agreement, which may result in immediate termination of your account. You agree to take reasonable measures to protect the security of your password.\n" +
            "You are responsible for all usage or activity on your account, including use of the account by any third party authorized by you to use your Doctor_id Name and Password. You shall notify ESTABLISHMENT LABS of any known or suspected unauthorized use(s) of your account, or any known or suspected breach of security, including loss, theft, or unauthorized disclosure of your password or any other relevant registration details provided.\n" +
            "\n" +
            "Site Privacy Statement\n" +
            "Our Privacy Statement is available on this Site and by accessing the Site, you are agreeing to be legally bound by the Privacy Policy. The Privacy Policy in its entirety is hereby incorporated into this Agreement by reference. To read our Privacy Policy please click here.\n" +
            "\n" +
            "Disclaimer of Warranties\n" +
            "THIS SITE CONTAINS INFORMATION CONCERNING ESTABLISHMENT LABS THAT MAY BE USEFUL TO OUR CUSTOMERS, EMPLOYEES, AND SHAREHOLDERS AS WELL AS TO THE GENERAL PUBLIC. HOWEVER, ESTABLISHMENT LABS MAKES NO REPRESENTATIONS OR WARRANTIES AS TO THE ACCURACY OF ANY INFORMATION CONTAINED HEREIN AND EXPRESSLY DISCLAIMS ANY OBLIGATION TO UPDATE SAID INFORMATION.\n" +
            "THE SITE AND THE CONTENT ARE PROVIDED ON AN “AS IS,” “AS AVAILABLE” BASIS, WITHOUT WARRANTIES OF ANY KIND. THERE MAY BE DELAYS, OMISSIONS OR INACCURACIES IN THE CONTENT AND THE SITE. ESTABLISHMENT LABS AND ITS AFFILIATES DO NOT WARRANT THE ACCURACY, COMPLETENESS, TIMELINESS, NON-INFRINGEMENT, TITLE, MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE OF THE CONTENT OR THE SITE ITSELF, AND WE HEREBY DISCLAIM ANY SUCH EXPRESS OR IMPLIED WARRANTIES. ESTABLISHMENT LABS DOES NOT REPRESENT OR WARRANT THAT THE CONTENT OF THIS SITE IS FREE OF VIRUSES, WORMS OR OTHER CODE THAT MAY MANIFEST CONTAMINATING OR DESTRUCTIVE PROPERTIES. BECAUSE SOME JURISDICTIONS DO NOT PERMIT THE EXCLUSION OF CERTAIN WARRANTIES, THESE EXCLUSIONS MAY NOT APPLY TO YOU.\n" +
            "\n" +
            "Limitation of Liability\n" +
            "YOUR USE OF THIS SITE IS AT YOUR SOLE RISK. UNDER NO CIRCUMSTANCES, SHALL ESTABLISHMENT LABS, ITS AFFILIATES OR ANY OF THEIR RESPECTIVE DIRECTORS, OFFICERS, EMPLOYEES, OR AGENTS, BE LIABLE FOR ANY DIRECT OR INDIRECT LOSSES OR DAMAGES ARISING OUT OF OR IN CONNECTION WITH YOUR USE OF OR INABILITY TO USE THIS SITE OR YOUR RELIANCE ON ANY CONTENT. THIS LIMITATION OF LIABILITY APPLIES TO ALL LOSSES AND DAMAGES OF ANY KIND WHATSOEVER, WHETHER DIRECT OR INDIRECT, GENERAL, SPECIAL, INCIDENTAL, CONSEQUENTIAL, EXEMPLARY OR OTHERWISE, INCLUDING WITHOUT LIMITATION, LOSS OF DATA, REVENUE OR PROFITS. THIS LIMITATION OF LIABILITY APPLIES WHETHER THE ALLEGED LIABILITY IS BASED ON CONTRACT, NEGLIGENCE, TORT, STRICT LIABILITY OR ANY OTHER BASIS AND EVEN IF AN AUTHORIZED REPRESENTATIVE OF ESTABLISHMENT LABS OR ITS AFFILIATES HAS BEEN ADVISED OF OR SHOULD HAVE KNOWN OF THE POSSIBILITY OF SUCH DAMAGES.\n" +
            "SOME COUNTRIES DO NOT ALLOW THE EXCLUSION OF DAMAGES OR LIMITATION OF LIABILITY SET FORTH ABOVE, SO THIS LIMITATION OF LIABILITY MAY NOT APPLY TO YOU. IF ANY PART OF THIS LIMITATION OF LIABILITY IS FOUND TO BE INVALID OR UNENFORCEABLE FOR ANY REASON, THEN THE AGGREGATE LIABILITY OF ESTABLISHMENT LABS AND/OR ITS AFFILIATES UNDER SUCH CIRCUMSTANCES SHALL NOT EXCEED, ONE HUNDRED ($100.00) DOLLARS IN THE AGGREGATE. IF ANY REMEDY HEREUNDER IS DETERMINED TO HAVE FAILED OF ITS ESSENTIAL PURPOSE, ALL LIMITATIONS OF LIABILITY, DISCLAIMERS AND EXCLUSIONS OF WARRANTIES AND DAMAGES SET FORTH IN THIS AGREEMENT SHALL REMAIN IN EFFECT.\n" +
            "\n" +
            "Forward Looking Statements\n" +
            "This site may from time to time contain certain forward-looking statements regarding ESTABLISHMENT LABS’s performance, including future revenues, products and income, or events or developments that ESTABLISHMENT LABS expects to occur or anticipates occurring in the future. All such statements are based upon current expectations of ESTABLISHMENT LABS and involve a number of business risks and uncertainties. Actual results could vary materially from anticipated results described, implied or projected in any forward-looking statement. Factors that could cause actual results to vary materially from any forward-looking statement include, but are not limited to: competitive factors; pricing and market share pressures; uncertainties of litigation; ESTABLISHMENT LABS’s ability to achieve sales and earnings forecasts, which are based on sales volume and product mix assumptions, to achieve its cost savings objectives, and to achieve anticipated synergies and other cost savings in connection with acquisitions; changes in regional, national or foreign economic conditions; increases in energy costs: fluctuations in costs and availability or raw materials and in ESTABLISHMENT LABS’s ability to maintain favorable supplier arrangements and relationships; changes in interest or foreign currency exchange rates; delays in product introductions; and changes in health care or other governmental regulation, as well as other factors discussed in this Site in ESTABLISHMENT LABS’s filings with the Securities and Exchange Commission. We do not intend to update any forward-looking statements.\n" +
            "\n" +
            "Indemnification\n" +
            "In the event that any legal action is taken resulting from (i) your use of the Content or Site, including your breach of the terms of this Agreement, or (ii) any Unsolicited Information provided by you, you agree to defend, indemnify, hold harmless and pay any reasonable legal and accounting fees without limitation incurred by ESTABLISHMENT LABS, its affiliates, its and their directors, officers, employees, agents, investors or licensors. ESTABLISHMENT LABS shall provide notice to you promptly of any such claim, suit, or proceeding. ESTABLISHMENT LABS shall have the right, at its option and expense, to participate in the defense and/or settlement of any claim or action, or to assume the exclusive defense and control of any matter otherwise subject to indemnification by you without relieving your indemnification obligations. In no event shall you settle any suit or claim imposing any liability or other obligations on ESTABLISHMENT LABS without its prior written consent.\n" +
            "\n" +
            "General\n" +
            "You are responsible for obtaining and maintaining all telephone, computer hardware and other equipment needed for access to and use of the Site and all charges related thereto. ESTABLISHMENT LABS reserves the right to alter or delete material from this Site at any time. This Site is controlled and operated by ESTABLISHMENT LABS Holdings Inc from its offices within Costa Rica. Any claim relating to, and the use of, this Site are governed by the laws of Costa Rica. By using this Site, you consent to personal jurisdiction in Costa Rica, for any action arising out of or relating to this Site or your use of this Site. Such courts shall have exclusive jurisdiction over all such actions. The United Nations Convention on Contacts for the International Sale of Goods does not apply to this Agreement.\n" +
            "This Agreement constitutes the entire agreement between you and ESTABLISHMENT LABS with respect to your access to and/or use of this Site. Any claims arising in connection with your use of the Site or any Content must be brought within one (1) year of the date of the event giving rise to such action occurred. All provisions of this Agreement pertaining to indemnification, disclaimer or warranties, limitation of liability and proprietary rights shall survive the termination of this Agreement for any reason. If any provision of this Agreement is invalid or unenforceable, the remaining provisions will continue in full force and effect, and the invalid or unenforceable provision will be deemed superseded by a valid enforceable provision that closely matches the intent of the original provision. The failure by us to exercise or enforce any right or provision under this Agreement shall not constitute a waiver of such right or provision. All rights not expressly granted herein are hereby reserved.";


    private static final String TAG = "SignupActivity";
    private static final String OPCION = "Opc";
    @BindView(R.id.chk_terms)
    CheckBox _terms;
    @BindView(R.id.sp_country)
    Spinner _country;
    @BindView(R.id.input_name)
    EditText _nameText;
    @BindView(R.id.input_lname)
    EditText _lnameText;
    @BindView(R.id.input_email)
    EditText _emailText;
    @BindView(R.id.input_password)
    EditText _passwordText;
    @BindView(R.id.input_reEnterPassword)
    EditText _reEnterPasswordText;
    @BindView(R.id.btn_signup)
    Button _signupButton;
    @BindView(R.id.btn_terms)
    Button _btn_terms;
    @BindView(R.id.btn_privacy)
    Button _btn_privacy;
    @BindView(R.id.btn_fb_login)
    Button _fb_login;
    @BindView(R.id.btn_google)
    Button _gog_login;
    @BindView(R.id.link_login)
    TextView _loginLink;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        ButterKnife.bind(this);
        Bundle x = this.getIntent().getExtras();

        if (x != null) {
            Doctor_id = x.getInt(OPCION);
        }
        mFacebook=new FacebookHelper(CreateAccountActivity.this);
        mGoogle=new GoogleHelper(this,this,null);


        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        //GOOGLE SIGN UP

        _gog_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                METHOD="G";
            mGoogle.performSignIn(CreateAccountActivity.this);

            }
        });

//FACEBOOK SIGn UP

        _fb_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mFacebook.performSignIn(CreateAccountActivity.this);


            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });


        _btn_privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogo(CreateAccountActivity.this, getString(R.string.privacy_policy), "Privacy Policy\n" +
                        "This privacy policy sets out how Establishment Labs S.A. uses and protects any information that you give Establishment Labs S.A. when you use this website. All personal data is stored in a secure data base.\n" +
                        "\n" +
                        "Establishment Labs S.A. is committed to ensuring that your privacy is protected. Should we ask you to provide certain information by which you can be identified when using this website, then you can be assured that it will only be used in accordance with this privacy statement.\n" +
                        "\n" +
                        "Establishment Labs S.A. may change this policy from time to time by updating this page. You should check this page from time to time to ensure that you agree with any changes implemented. This policy is effective from March 1st, 2010. (Latest Revision: March 1st, 2014)\n" +
                        "\n" +
                        "\n" +
                        "What we collect\n" +
                        "We may collect the following information:\n" +
                        "\n" +
                        "Name\n" +
                        "Address\n" +
                        "E-mail address\n" +
                        "Telephone number\n" +
                        "Implants serial numbers\n" +
                        "Name of attending surgeons\n" +
                        "Procedures dates\n" +
                        "You may decide not to provide the information requested in the forms or anywhere in the website, but the submittal of the complete information required may be a condition to receive certain benefits, warranties or coverage that will not be granted, provided or furnished unless the complete information is provided.\n" +
                        "\n" +
                        "What we do with the information we gather\n" +
                        "We will not share your medical information except as required by the law or with your express authorization. We will use your personal data and information in accordance and conformity with the terms described in the Directive 95/46/EC of the European Parliament and the Council of 24 October 1995, and other applicable international and local regulations.\n" +
                        "\n" +
                        "We require this information to understand your needs and provide you with a better service, and in particular for the following reasons:\n" +
                        "\n" +
                        "Internal record keeping.\n" +
                        "Mandatory Materiovigilance purposes.\n" +
                        "Ascertain and verification of your participation in determined warranty or insurance programs.\n" +
                        "We may use your information to contact you if there may be any important health information you should be given.\n" +
                        "We may use the information to improve our products and services.\n" +
                        "We may send you emails about new products, special offers or other information which we think you may find interesting using the email address which you have provided.\n" +
                        "From time to time, we may also use your information to contact you for market research or medical research purposes. We may contact you by email, phone, fax or mail. We may use the information to customize the website according to your interests.\n" +
                        "We may share the Information with your consent or as you may direct.\n" +
                        "We may share the information with our insurance providers, who shall protect your information with the same care and only use your information in accordance with our instructions.\n" +
                        "We may share the information with our service providers, who shall protect your information and only use your information in accordance with our instructions.\n" +
                        "We may also disclose information when required by law or by the courts of the countries where we have operations.\n" +
                        "Security\n" +
                        "We are committed to ensuring that your information is secure. In order to prevent unauthorized access or disclosure, we have put in place suitable physical, electronic and managerial procedures to safeguard and secure the information we collect online.\n" +
                        "\n" +
                        "How we use cookies\n" +
                        "A cookie is a small file which asks permission to be placed on your computer’s hard drive. Once you agree, the file is added and the cookie helps analyze web traffic or lets you know when you visit a particular site. Cookies allow web applications to respond to you as an individual. The web application can tailor its operations to your needs, likes and dislikes by gathering and remembering information about your preferences.\n" +
                        "\n" +
                        "We use traffic log cookies to identify which pages are being used. This helps us analyze data about web page traffic and improve our website in order to tailor it to customer needs. We only use this information for statistical analysis purposes and then the data is removed from the system.\n" +
                        "\n" +
                        "Overall, cookies help us provide you with a better website, by enabling us to monitor which pages you find useful and which you do not. A cookie in no way gives us access to your computer or any information about you, other than the data you choose to share with us.\n" +
                        "\n" +
                        "You can choose to accept or decline cookies. Most web browsers automatically accept cookies, but you can usually modify your browser setting to decline cookies if you prefer. This may prevent you from taking full advantage of the website.\n" +
                        "\n" +
                        "Controlling your personal information\n" +
                        "You may choose to restrict the collection or use of your personal information in the following ways:\n" +
                        "\n" +
                        "If you have previously agreed to us using your personal information for direct marketing purposes, you may change your mind (opt-out) at any time by writing to or emailing us at info@establishmentlabs.com\n" +
                        "\n" +
                        "You may also request to exclude from our data base any information which is not required by materiovigilance regulations to be kept. Nevertheless, that will also be understood as a resignation of any right or claim which exists or may exist, on any program, warranty or coverage which may have been granted with the condition of providing the required information.\n" +
                        "\n" +
                        "If you believe that any information we are holding on you is incorrect or incomplete, or there is any information that may have changed or needs updating, please write to or email us as soon as possible, at the address info@establishmentlabs.com. We will promptly correct any information found to be incorrect.\n" +
                        "\n" +
                        "We will not sell, distribute or lease your personal information to third parties unless we are required by law to do so. We may use your personal information to send you promotional information which we think you may find interesting.\n" +
                        "\n" +
                        "Links to other websites\n" +
                        "Our website may contain links to other websites of interest. However, once you have used these links to leave our site, you should note that we do not have any control over that other website. Therefore, we cannot be responsible for the protection and privacy of any information which you provide whilst visiting such sites and such sites are not governed by this privacy statement. You should exercise caution and look at the privacy statement applicable to the website in question.");
            }
        });

        _btn_terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogo(CreateAccountActivity.this, getString(R.string.terms_and_conditions), TERMS);
            }
        });


        List<String> types = new ArrayList<String>();
        types.add(getString(R.string.Patient));
        types.add(getString(R.string.Doctor));


        Type listType = new TypeToken<ArrayList<Country>>() {
        }.getType();
        Gson gson = new Gson();
        String COUNTRIES = "[{\"id\":29,\"name\":\"Germany\"},{\"id\":42,\"name\":\"Sweden\"},{\"id\":154,\"name\":\"Canada\"},{\"id\":174,\"name\":\"United States\"}]";
        paises = gson.fromJson(COUNTRIES, listType);

        List<String> countries = new ArrayList<String>();
        for (int i = 0; i < paises.size(); i++) {
            countries.add(paises.get(i).getName());
        }



        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, countries);


        // Drop down layout style - list view with radio button
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        _country.setAdapter(dataAdapter2);
    }


    public void signup() {

        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        progressDialog = new ProgressDialog(CreateAccountActivity.this,
                R.style.AppThemeMain2_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");

        String name = _nameText.getText().toString();
        String lastname = _lnameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        String reEnterPassword = _reEnterPasswordText.getText().toString();



        // TODO: Implement your own signup logic here.
        if(_terms.isChecked()){

            if(CheckNetwork.isInternetAvailable(this))  //if connection available
            {
                requestSignUp(email, name, lastname, METHOD, "A", paises.get(_country.getSelectedItemPosition()).getId(), password, TOKEN);
            }else {
                Dialogo.messageDialog(this,"Network Connection","No Internet Connection");
            }
        }else {
            dialogo(CreateAccountActivity.this,"TERMS AND PRIVACY POLICY","You must accept the terms and conditions and privacy policy before");
        }



      /*  new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);*/
    }

    private void requestSignUp(String email, String name, String lastname, String method, String platform, int country_id, String password, String token) {
        Controller.getInstance().Register(this, name, lastname, email, method, platform, country_id, password, token, new CreateAccountActivity.RegisterCallback());
    }

    public void dialogo(Context context, String titulo, String mensaje) {

        AlertDialog dialog = new AlertDialog.Builder(context)

                .setTitle(titulo)
                .setMessage(mensaje)
                .setPositiveButton(R.string.Accept, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        _signupButton.setEnabled(true);
                    }
                })
                .setIcon(R.drawable.ic_error_black_24dp)
                .show();
        TextView textView = (TextView) dialog.findViewById(android.R.id.message);
        textView.setScroller(new Scroller(dialog.getContext()));
        textView.setVerticalScrollBarEnabled(true);
        textView.setMovementMethod(new ScrollingMovementMethod());

    }


    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String lname = _lnameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        String reEnterPassword = _reEnterPasswordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (lname.isEmpty()) {
            _lnameText.setError("at least 3 characters");
            valid = false;
        } else {
            _lnameText.setError(null);
        }


        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email lname");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if(METHOD.equalsIgnoreCase("E")){

            if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
                _passwordText.setError("between 4 and 10 alphanumeric characters");
                valid = false;
            } else {
                _passwordText.setError(null);
            }

            if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) {
                _reEnterPasswordText.setError("Password Do not match");
                valid = false;
            } else {
                _reEnterPasswordText.setError(null);
            }
        }

        return valid;
    }


    public static void createInstance(Activity activity, int usuario) {
        Intent intent = getLaunchIntent(activity, usuario);
        activity.startActivity(intent);
    }

    public static Intent getLaunchIntent(Context context, int usuario) {
        Intent intent = new Intent(context, CreateAccountActivity.class);
        intent.putExtra(OPCION, usuario);
        return intent;
    }



 /*   @Override
    public void onGoogleAuthSignIn(String s, String s1) {
        METHOD = "G";
        google_signup(s);
        this.


    }


*/

    @Override
    public void onGoogleAuthSignIn(String authToken, String userId,String email, String lastname, String name, String url) {
        parameters_signup(name,lastname);
        _emailText.setText(email);
        TOKEN=userId;
        PHOTO=url;
    }

    @Override
    public void onGoogleAuthSignInFailed(String errorMessage) {

    }

    @Override
    public void onGoogleAuthSignOut() {

    }

    @Override
    public void onFbSignInFail(String errorMessage) {

    }

    @Override
    public void onFbSignInSuccess(String authToken, String userId, String name, String Lastname, String picture) throws ExecutionException, InterruptedException {
        TOKEN=userId;
        PHOTO=picture;
        METHOD="F";
        requestEmail(AccessToken.getCurrentAccessToken());
        parameters_signup(name,Lastname);

    }

    @Override
    public void onFBSignOut() {

    }


    class RegisterCallback implements LoginListener {

        @Override
        public void onLoginStart() {
            progressDialog.show();
        }

        @Override
        public void onLoginCompleted(User user) {
            mydb = new DB(CreateAccountActivity.this);
            if (mydb.insertUser(user.getId(), user.getName(), user.getLastname(), user.getCountry_id(), user.getApp_token(), user.getType(), user.getEmail(), Doctor_id,METHOD,PHOTO)) {
                progressDialog.dismiss();
                Main2Activity.createInstance(CreateAccountActivity.this,user.getType(),user);
            }
        }

        @Override
        public void onLoginError(Error message) {

            String mensaje = "Unknown Error";
            if (message.getCode().equals("103")){
                mensaje = getString(R.string.this_email_address_is_already_registered);
            }else  if (message.getCode().equals("301")){
                mensaje= "Error Interpreting Information";
            }
            progressDialog.dismiss();
            Snackbar snackbar = Snackbar
                    .make(getCurrentFocus(),mensaje, Snackbar.LENGTH_INDEFINITE)
                    .setAction(getString(R.string.Dissmiss), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });
            snackbar.setActionTextColor(Color.WHITE);
            snackbar.show();


        }

/*        @Override
        public void onUserInfoStart() {
            //Nothing
        }



        @Override
        public void onUserInfoCompleted(Doctor_id user) {
            //logueo
            mydb = new DB(LoginActivity.this);
           if( mydb.insertUser(user.getId(),user.getName(),user.getLastname(),user.getEmail(),user.getType(),user.getType_id(),user.getMethod(),user.getToken(),user.getApptoken(),user.getPicture(),user.getDoctor_id())){
               progressDialog.dismiss();
               Main2Activity.createInstance(LoginActivity.this,1,"n",user);
           }

}

        @Override
        public void onUserInfoError(String message) {
            progressDialog.dismiss();
            Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
        }*/
    }

    public void parameters_signup(String name, String lastname) {

            _nameText.setText(name);
            _lnameText.setText(lastname);
            _passwordText.setVisibility(View.GONE);
            _reEnterPasswordText.setVisibility(View.GONE);

       // _signupButton.setEnabled(true);


/*             signup();*/
        }







    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mFacebook.onActivityResult(requestCode, resultCode, data);
        mGoogle.onActivityResult(requestCode, resultCode, data);
/*
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }*/
    }

    @Override
    public void onDestroy() {
        super.onDestroy();


    }

    private void requestEmail(AccessToken currentAccessToken) {
        GraphRequest request = GraphRequest.newMeRequest(currentAccessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                if (response.getError() != null) {
                    Toast.makeText(getApplicationContext(), response.getError().getErrorMessage(), Toast.LENGTH_LONG).show();
                    return;
                }
                try {
                    String email = object.getString("email");
                    setEmail(email);
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id, first_name, last_name, email, gender, birthday, location");
        request.setParameters(parameters);
        request.executeAsync();
    }


    private void setEmail(String email) {
        _emailText.setText(email);
    }



}

