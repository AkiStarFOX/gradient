precision highp float;

uniform sampler2D u_Texture;

uniform float u_u1;
uniform float u_u2;
uniform float u_Disp1;
uniform float u_Disp2;
uniform float u_color1;
uniform float u_color2;
uniform float u_color3;

uniform float u_FirstColorRed;
uniform float u_FirstColorGreen;
uniform float u_FirstColorBlue;
uniform float u_SecondColorRed;
uniform float u_SecondColorGreen;
uniform float u_SecondColorBlue;
uniform float u_RazColorRed;
uniform float u_RazColorGreen;
uniform float u_RazColorBlue;
uniform float u_Pulse;

uniform float u_gradient2_u1;
uniform float u_gradient2_u2;
uniform float u_gradient2_Disp1;
uniform float u_gradient2_Disp2;

uniform float u_gradient3_u1;
uniform float u_gradient3_u2;
uniform float u_gradient3_Disp1;
uniform float u_gradient3_Disp2;

uniform float u_gradient4_u1;
uniform float u_gradient4_u2;
uniform float u_gradient4_Disp1;
uniform float u_gradient4_Disp2;


uniform float u_gradient2_FirstColorRed;
uniform float u_gradient2_FirstColorGreen;
uniform float u_gradient2_FirstColorBlue;
uniform float u_gradient2_SecondColorRed;
uniform float u_gradient2_SecondColorGreen;
uniform float u_gradient2_SecondColorBlue;
uniform float u_gradient2_RazColorRed;
uniform float u_gradient2_RazColorGreen;
uniform float u_gradient2_RazColorBlue;
uniform float u_gradient2_Pulse;

uniform float u_gradient3_FirstColorRed;
uniform float u_gradient3_FirstColorGreen;
uniform float u_gradient3_FirstColorBlue;
uniform float u_gradient3_SecondColorRed;
uniform float u_gradient3_SecondColorGreen;
uniform float u_gradient3_SecondColorBlue;
uniform float u_gradient3_RazColorRed;
uniform float u_gradient3_RazColorGreen;
uniform float u_gradient3_RazColorBlue;
uniform float u_gradient3_Pulse;

uniform float u_gradient4_FirstColorRed;
uniform float u_gradient4_FirstColorGreen;
uniform float u_gradient4_FirstColorBlue;
uniform float u_gradient4_SecondColorRed;
uniform float u_gradient4_SecondColorGreen;
uniform float u_gradient4_SecondColorBlue;
uniform float u_gradient4_RazColorRed;
uniform float u_gradient4_RazColorGreen;
uniform float u_gradient4_RazColorBlue;
uniform float u_gradient4_Pulse;
uniform int u_kolichestvoGrad;

uniform float u_gradient2_color1;
uniform float u_gradient2_color2;
uniform float u_gradient2_color3;

uniform float u_gradient3_color1;
uniform float u_gradient3_color2;
uniform float u_gradient3_color3;

uniform float u_gradient4_color1;
uniform float u_gradient4_color2;
uniform float u_gradient4_color3;

uniform float u_Alpha;
uniform float u_gradient2_Alpha;
uniform float u_gradient3_Alpha;
uniform float u_gradient4_Alpha;
//
uniform float u_gradient1_Type;
uniform float u_gradient2_Type;
uniform float u_gradient3_Type;
uniform float u_gradient4_Type;





varying highp vec2 v_TexCoord;

float formula(float u_Disp1,float u_Disp2,float u_Pulse,float u_u1, float u_u2) {
    float  x = v_TexCoord.x;
    float y = v_TexCoord.y;
    float u_Disp12= 1.0;
    float p = ((u_Disp12/(u_Disp1*u_Disp2))/100.0);
    float x1 = (pow((x-u_u1),2.0))/pow(u_Disp1,2.0);
    float y1 = (pow((y-u_u2),2.0))/pow(u_Disp2,2.0);
    float xy = p * ((2.0*(x-u_u1)*(y-u_u2))/(u_Disp1*u_Disp2));
    float stepenE = (-1.0*(1.0/(2.0*(1.0-pow(p,2.0)))))*(x1-xy+y1);
    float e = 2.71828;
    float result = (1.0 /(2.0 * 3.14 * u_Disp1 * u_Disp2 *sqrt((1.0-pow(p,2.0))))) * pow(e,stepenE);
    float result2 = result/(1.0/(2.0*3.14*sqrt(u_Disp1*u_Disp2)));
    return result2;
}

float formulaTAN(){
    float x1=v_TexCoord.x;
    float y1=v_TexCoord.y;
    float result = atan((pow(x1,x1)-y1));
    return result;
}

void main(void) {
    float gradient1_colorRed;
    float gradient1_colorGreen;
    float gradient1_colorBlue;
    float gradient2_colorRed = 0.0;
    float gradient2_colorGreen = 0.0;
    float gradient2_colorBlue = 0.0;
    float gradient3_colorRed = 0.0;
    float gradient3_colorGreen = 0.0;
    float gradient3_colorBlue = 0.0;
    float gradient4_colorRed = 0.0;
    float gradient4_colorGreen = 0.0;
    float gradient4_colorBlue = 0.0;
    float gradient1_alpha = 0.0;
    float gradient2_alpha = 0.0;
    float gradient3_alpha = 0.0;
    float gradient4_alpha = 0.0;
    float gradient1_colorRed_Tan = 0.0;
    float gradient1_colorGreen_Tan = 0.0;
    float gradient1_colorBlue_Tan = 0.0;
    float gradient2_colorRed_Tan = 0.0;
    float gradient2_colorGreen_Tan = 0.0;
    float gradient2_colorBlue_Tan = 0.0;
    float gradient3_colorRed_Tan = 0.0;
    float gradient3_colorGreen_Tan = 0.0;
    float gradient3_colorBlue_Tan = 0.0;
    float gradient4_colorRed_Tan = 0.0;
    float gradient4_colorGreen_Tan = 0.0;
    float gradient4_colorBlue_Tan = 0.0;


    float formula1 = formula(u_Disp1, u_Disp2, u_Pulse, u_u1, u_u2);
    float formula2 = formula(u_gradient2_Disp1, u_gradient2_Disp2, u_gradient2_Pulse, u_gradient2_u1, u_gradient2_u2);
    float formula3 = formula(u_gradient3_Disp1, u_gradient3_Disp2, u_gradient3_Pulse, u_gradient3_u1, u_gradient3_u2);
    float formula4 = formula(u_gradient4_Disp1, u_gradient4_Disp2, u_gradient4_Pulse, u_gradient4_u1, u_gradient4_u2);

    float formula_Tan = formulaTAN();



    if (u_gradient1_Type == 1.0){
        gradient1_colorRed = u_color1+clamp((u_SecondColorRed+u_RazColorRed*formula1),-1.0,1.0);
        gradient1_colorGreen = u_color2+clamp((u_SecondColorGreen+u_RazColorGreen*formula1),-1.0,1.0);
        gradient1_colorBlue = u_color3+clamp((u_SecondColorBlue+u_RazColorBlue*formula1),-1.0,1.0);
        gradient1_alpha = 1.0-(u_Alpha*formula1);
    }

    if (u_gradient1_Type == 2.0){
        gradient1_colorRed = u_color1+clamp((u_SecondColorRed+u_RazColorRed*formula_Tan),-1.0,1.0);
        gradient1_colorGreen = u_color2+clamp((u_SecondColorGreen+u_RazColorGreen*formula_Tan),-1.0,1.0);
        gradient1_colorBlue = u_color3+clamp((u_SecondColorBlue+u_RazColorBlue*formula_Tan),-1.0,1.0);
    }

    if (u_gradient1_Type==3.0){
        gradient1_colorRed=u_FirstColorRed*formula1;
        gradient1_colorGreen=u_FirstColorGreen*formula1;
        gradient1_colorBlue=u_FirstColorBlue*formula1;
        gradient1_alpha = formula1;
    }

    if (u_kolichestvoGrad>1){
        if (u_gradient2_Type==1.0){
            gradient2_colorRed = u_gradient2_color1+clamp((u_gradient2_SecondColorRed+u_gradient2_RazColorRed*formula2),-1.0,1.0);
            gradient2_colorGreen = u_gradient2_color2+clamp((u_gradient2_SecondColorGreen+u_gradient2_RazColorGreen*formula2),-1.0,1.0);
            gradient2_colorBlue = u_gradient2_color3+clamp((u_gradient2_SecondColorBlue+u_gradient2_RazColorBlue*formula2),-1.0,1.0);
            gradient2_alpha = 1.0-(u_gradient2_Alpha*formula2);
        }

        if (u_gradient2_Type==2.0){
            gradient2_colorRed= u_gradient2_color1+clamp((u_gradient2_SecondColorRed+u_gradient2_RazColorRed*formula_Tan),-1.0,1.0);
            gradient2_colorGreen = u_gradient2_color2+clamp((u_gradient2_SecondColorGreen+u_gradient2_RazColorGreen*formula_Tan),-1.0,1.0);
            gradient2_colorBlue = u_gradient2_color3+clamp((u_gradient2_SecondColorBlue+u_gradient2_RazColorBlue*formula_Tan),-1.0,1.0);
        }

        if (u_gradient2_Type==3.0){
            gradient2_colorRed=u_gradient2_FirstColorRed*formula2;
            gradient2_colorGreen=u_gradient2_FirstColorGreen*formula2;
            gradient2_colorBlue=u_gradient2_FirstColorBlue*formula2;
            gradient2_alpha = formula2;
        }
    }

    if (u_kolichestvoGrad>2){
        if (u_gradient3_Type==1.0){
            gradient3_colorRed = u_gradient3_color1+clamp((u_gradient3_SecondColorRed+u_gradient3_RazColorRed*formula3),-1.0,1.0);
            gradient3_colorGreen = u_gradient3_color2+clamp((u_gradient3_SecondColorGreen+u_gradient3_RazColorGreen*formula3),-1.0,1.0);
            gradient3_colorBlue = u_gradient3_color3+clamp((u_gradient3_SecondColorBlue+u_gradient3_RazColorBlue*formula3),-1.0,1.0);
            gradient3_alpha = 1.0-(u_gradient3_Alpha*formula3);
        }

        if (u_gradient3_Type==2.0){
            gradient3_colorRed = u_gradient3_color1+clamp((u_gradient3_SecondColorRed+u_gradient3_RazColorRed*formula_Tan),-1.0,1.0);
            gradient3_colorGreen= u_gradient3_color2+clamp((u_gradient3_SecondColorGreen+u_gradient3_RazColorGreen*formula_Tan),-1.0,1.0);
            gradient3_colorBlue = u_gradient3_color3+clamp((u_gradient3_SecondColorBlue+u_gradient3_RazColorBlue*formula_Tan),-1.0,1.0);
        }

        if (u_gradient3_Type==3.0){
            gradient3_colorRed=u_gradient3_FirstColorRed*formula3;
            gradient3_colorGreen=u_gradient3_FirstColorGreen*formula3;
            gradient3_colorBlue=u_gradient3_FirstColorBlue*formula3;
            gradient3_alpha = formula3;
        }
    }

    if (u_kolichestvoGrad>3){
        if (u_gradient4_Type==1.0){
            gradient4_colorRed =u_gradient4_color1+clamp((u_gradient4_SecondColorRed+u_gradient4_RazColorRed*formula4),-1.0,1.0);
            gradient4_colorGreen = u_gradient4_color2+clamp((u_gradient4_SecondColorGreen+u_gradient4_RazColorGreen*formula4),-1.0,1.0);
            gradient4_colorBlue = u_gradient4_color3+clamp((u_gradient4_SecondColorBlue+u_gradient4_RazColorBlue*formula4),-1.0,1.0);
            gradient4_alpha = 1.0-(u_gradient4_Alpha*formula4);
        }

        if (u_gradient4_Type==2.0){
            gradient4_colorRed=u_gradient4_color1+clamp((u_gradient4_SecondColorRed+u_gradient4_RazColorRed*formula_Tan),-1.0,1.0);
            gradient4_colorGreen = u_gradient4_color2+clamp((u_gradient4_SecondColorGreen+u_gradient4_RazColorGreen*formula_Tan),-1.0,1.0);
            gradient4_colorBlue = u_gradient4_color3+clamp((u_gradient4_SecondColorBlue+u_gradient4_RazColorBlue*formula_Tan),-1.0,1.0);
        }

        if (u_gradient4_Type==3.0){
            gradient4_colorRed=u_gradient4_FirstColorRed*formula4;
            gradient4_colorGreen=u_gradient4_FirstColorGreen*formula4;
            gradient4_colorBlue=u_gradient4_FirstColorBlue*formula4;
            gradient4_alpha = formula4;

        }
    }










	gl_FragColor = vec4( clamp((gradient1_colorRed+gradient2_colorRed+gradient3_colorRed+gradient4_colorRed),-1.0,1.0),
	clamp((gradient1_colorGreen+gradient2_colorGreen+gradient3_colorGreen+gradient4_colorGreen),-1.0,1.0),
	clamp((gradient1_colorBlue+gradient2_colorBlue+gradient3_colorBlue+gradient4_colorBlue),-1.0,1.0),
	clamp((gradient1_alpha+gradient2_alpha+gradient3_alpha+gradient4_alpha),0.0,1.0));




}