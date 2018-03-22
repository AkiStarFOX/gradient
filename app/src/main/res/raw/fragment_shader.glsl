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
const float PI_2 = 1.57079632679489661923;




varying highp vec2 v_TexCoord;

float formula(float u_Disp1,float u_Disp2,float u_Pulse,float u_u1, float u_u2) {
    float  x = v_TexCoord.x;
    float y = v_TexCoord.y;
    float u_Disp12= 1.0;
    float p = 0.01;
    float x1 = (pow((x-u_u1),2.0))/pow(u_Disp1,2.0);
    float y1 = (pow((y-u_u2),2.0))/pow(u_Disp2,2.0);
    float xy = p * ((2.0*(x-u_u1)*(y-u_u2))/(u_Disp1*u_Disp2));
    float stepenE = (-1.0*(1.0/(2.0*(1.0-pow(p,2.0)))))*(x1-xy+y1);
    float e = 2.71828;
    float result = (1.0 /(2.0 * 3.14 * u_Disp1 * u_Disp2 )) * pow(e,stepenE);
    float result2 = result/(1.0/(2.0*3.14*u_Disp1*u_Disp2));
    return result2;
}
float map(float value, float inMin, float inMax, float outMin, float outMax) {
    return (value - inMin) * (outMax - outMin) / (inMax - inMin) + outMin;
}

float formulaTAN(){
    float x1=v_TexCoord.x;
    float y1=v_TexCoord.y;
    float result = atan((pow(x1,x1)-y1));

    return result;
}
float formula4(float u_u1,float u_u2){
     float x=(v_TexCoord.x+v_TexCoord.x)-1.0;
     float y=(v_TexCoord.y+v_TexCoord.y)-1.0;
     float result =u_u1+pow(x,2.0)-pow(y,2.0)+u_u2;


     return map(result,-1.0,1.0,0.0,1.0);
}
float formula5(){
    float x=(v_TexCoord.x+v_TexCoord.x)-1.0;
    float y=(v_TexCoord.y+v_TexCoord.y)-1.0;
    float result = -((x*pow(y,3.0)-pow(x,3.0)*y)+u_u1/10.0)/((pow(x,2.0)+pow(y,2.0))+u_u2/10.0);

    return map(result,-1.0,1.0,0.0,1.0);
}
float rand(float x, float y)
{
    return fract(sin(dot(vec2(x, y), vec2(12.9898, 78.233))) * 43758.5453);
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
    vec3 c;


    float formula1_g1 = formula(u_Disp1, u_Disp2, u_Pulse, u_u1, u_u2);
    float formula1_g2 = formula(u_gradient2_Disp1, u_gradient2_Disp2, u_gradient2_Pulse, u_gradient2_u1, u_gradient2_u2);
    float formula1_g3 = formula(u_gradient3_Disp1, u_gradient3_Disp2, u_gradient3_Pulse, u_gradient3_u1, u_gradient3_u2);
    float formula1_g4 = formula(u_gradient4_Disp1, u_gradient4_Disp2, u_gradient4_Pulse, u_gradient4_u1, u_gradient4_u2);

    float formula_Tan = formulaTAN();
    float formula4_g1 = formula4(u_u1,u_u2);
    float formula4_g2 = formula4(u_gradient2_u1,u_gradient2_u2);

    float formula5_g1 = formula5();



    if (u_gradient1_Type == 1.0){
        gradient1_colorRed = u_color1+clamp((u_SecondColorRed+u_RazColorRed*formula1_g1),0.0,1.0);
        gradient1_colorGreen = u_color2+clamp((u_SecondColorGreen+u_RazColorGreen*formula1_g1),0.0,1.0);
        gradient1_colorBlue = u_color3+clamp((u_SecondColorBlue+u_RazColorBlue*formula1_g1),0.0,1.0);
        gradient1_alpha=1.0;

    }

    if (u_gradient1_Type == 2.0){
        gradient1_colorRed = u_color1+u_FirstColorRed;
        gradient1_colorGreen = u_color2+u_FirstColorGreen;
        gradient1_colorBlue = u_color3+u_FirstColorBlue;
//        gradient1_colorRed = mix(u_SecondColorRed, u_RazColorRed, formula_Tan);
//        gradient1_colorGreen = mix(u_SecondColorGreen, u_RazColorGreen, formula_Tan);
//        gradient1_colorBlue = mix(u_SecondColorBlue, u_RazColorBlue, formula_Tan);
        gradient1_alpha=formula_Tan;

    }

    if (u_gradient1_Type==3.0){
        gradient1_colorRed=u_color1+u_FirstColorRed;
        gradient1_colorGreen=u_color2+u_FirstColorGreen;
        gradient1_colorBlue=u_color3+u_FirstColorBlue;
        gradient1_alpha = formula1_g1;
    }

    if (u_gradient1_Type==4.0){
        gradient1_colorRed=u_color1+u_FirstColorRed;
        gradient1_colorGreen=u_color2+u_FirstColorGreen;
        gradient1_colorBlue=u_color3+u_FirstColorBlue;
        gradient1_alpha = formula4_g1;
    }
    if (u_gradient1_Type==5.0){
        gradient1_colorRed=u_color1+u_FirstColorRed;
        gradient1_colorGreen=u_color2+u_FirstColorGreen;
        gradient1_colorBlue=u_color3+u_FirstColorBlue;
        gradient1_alpha = formula5_g1;
    }

    if (u_kolichestvoGrad>1){
        if (u_gradient2_Type==1.0){
            gradient2_colorRed = u_gradient2_color1+clamp((u_gradient2_SecondColorRed+u_gradient2_RazColorRed*formula1_g2),-1.0,1.0);
            gradient2_colorGreen = u_gradient2_color2+clamp((u_gradient2_SecondColorGreen+u_gradient2_RazColorGreen*formula1_g2),-1.0,1.0);
            gradient2_colorBlue = u_gradient2_color3+clamp((u_gradient2_SecondColorBlue+u_gradient2_RazColorBlue*formula1_g2),-1.0,1.0);
            gradient2_alpha = 1.0;
        }

        if (u_gradient2_Type==2.0){
            gradient2_colorRed= u_gradient2_color1+(u_gradient2_FirstColorRed);
            gradient2_colorGreen = u_gradient2_color2+(u_gradient2_FirstColorGreen);
            gradient2_colorBlue = u_gradient2_color3+(u_gradient2_FirstColorBlue);
            gradient2_alpha=formula_Tan;
        }

        if (u_gradient2_Type==3.0){
            gradient2_colorRed=u_gradient2_color1+(u_gradient2_FirstColorRed);
            gradient2_colorGreen=u_gradient2_color2+(u_gradient2_FirstColorGreen);
            gradient2_colorBlue=u_gradient2_color3+(u_gradient2_FirstColorBlue);
            gradient2_alpha = formula1_g2;
        }
        if (u_gradient1_Type==4.0){
            gradient2_colorRed= u_gradient2_color1+(u_gradient2_FirstColorRed);
            gradient2_colorGreen = u_gradient2_color2+(u_gradient2_FirstColorGreen);
            gradient2_colorBlue = u_gradient2_color3+(u_gradient2_FirstColorBlue);
            gradient2_alpha=formula4_g2;
            }
    }

    if (u_kolichestvoGrad>2){
        if (u_gradient3_Type==1.0){
            gradient3_colorRed = u_gradient3_color1+clamp((u_gradient3_SecondColorRed+u_gradient3_RazColorRed*formula1_g3),-1.0,1.0);
            gradient3_colorGreen = u_gradient3_color2+clamp((u_gradient3_SecondColorGreen+u_gradient3_RazColorGreen*formula1_g3),-1.0,1.0);
            gradient3_colorBlue = u_gradient3_color3+clamp((u_gradient3_SecondColorBlue+u_gradient3_RazColorBlue*formula1_g3),-1.0,1.0);
            gradient3_alpha = 1.0-(u_gradient3_Alpha);
        }

        if (u_gradient3_Type==2.0){
            gradient3_colorRed = u_gradient3_color1+u_gradient3_FirstColorRed;
            gradient3_colorGreen= u_gradient3_color2+u_gradient3_FirstColorGreen;
            gradient3_colorBlue = u_gradient3_color3+u_gradient3_FirstColorBlue;
            gradient3_alpha=formula_Tan;
        }

        if (u_gradient3_Type==3.0){
            gradient3_colorRed=u_gradient3_color1+u_gradient3_FirstColorRed;
            gradient3_colorGreen=u_gradient3_color2+u_gradient3_FirstColorGreen;
            gradient3_colorBlue=u_gradient3_color3+u_gradient3_FirstColorBlue;
            gradient3_alpha = formula1_g3;
        }
    }

    if (u_kolichestvoGrad>3){
        if (u_gradient4_Type==1.0){
            gradient4_colorRed =u_gradient4_color1+clamp((u_gradient4_SecondColorRed+u_gradient4_RazColorRed*formula1_g4),-1.0,1.0);
            gradient4_colorGreen = u_gradient4_color2+clamp((u_gradient4_SecondColorGreen+u_gradient4_RazColorGreen*formula1_g4),-1.0,1.0);
            gradient4_colorBlue = u_gradient4_color3+clamp((u_gradient4_SecondColorBlue+u_gradient4_RazColorBlue*formula1_g4),-1.0,1.0);
            gradient4_alpha = 1.0;
        }

        if (u_gradient4_Type==2.0){
            gradient4_colorRed=u_gradient4_color1+u_gradient4_FirstColorRed;
            gradient4_colorGreen = u_gradient4_color2+u_gradient4_FirstColorGreen;
            gradient4_colorBlue = u_gradient4_color3+u_gradient4_FirstColorBlue;
            gradient4_alpha=formula_Tan;
        }

        if (u_gradient4_Type==3.0){
            gradient4_colorRed=u_gradient4_color1+u_gradient4_FirstColorRed;
            gradient4_colorGreen=u_gradient4_color2+u_gradient4_FirstColorGreen;
            gradient4_colorBlue=u_gradient4_color3+u_gradient4_FirstColorBlue;
            gradient4_alpha = formula1_g4;

        }
    }

    c = mix(vec3(0,0,0),vec3(gradient1_colorRed,gradient1_colorGreen,gradient1_colorBlue),gradient1_alpha) ;
    c = mix(c,vec3(gradient2_colorRed,gradient2_colorGreen,gradient2_colorBlue),gradient2_alpha);
    c = mix(c,vec3(gradient3_colorRed,gradient3_colorGreen,gradient3_colorBlue),gradient3_alpha);
    c = mix(c,vec3(gradient4_colorRed,gradient4_colorGreen,gradient4_colorBlue),gradient4_alpha);

//    c.rgb = vec3(gradient1_colorRed, gradient1_colorGreen, gradient1_colorBlue);
//    c.rgb += mix(-1.0/255.0, 1.0/255.0, rand(v_TexCoord.y, v_TexCoord.x));

     gl_FragColor = vec4(c+0.1,1.0);





}