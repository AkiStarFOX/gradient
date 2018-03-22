precision highp float;

varying highp vec2 v_GCoord; // координаты градиента, -0.5..0.5 (у контура фигуры может выходить за этот интервал)

float map(float value, float inMin, float inMax, float outMin, float outMax) {
    return (value - inMin) * (outMax - outMin) / (inMax - inMin) + outMin;
}

void main() {
    // линейный
	vec2 u_GStart = vec2(-1.0, -1.0);
	vec2 u_GEnd = vec2(1.0, 1.0);
	vec4 u_Color1 = vec4(1.0, 0.0, 0.0, 1.0);
	vec4 u_Color2 = vec4(0.0, 0.0, 1.0, 1.0);
	
	float a = atan(-u_GEnd.y + u_GStart.y, u_GEnd.x - u_GStart.x);
	float srx = u_GStart.x * cos(a) - u_GStart.y * sin(a);
	float erx = u_GEnd.x * cos(a) - u_GEnd.y * sin(a);
	float xlr = v_GCoord.x * cos(a) - v_GCoord.y * sin(a);
	
    gl_FragColor = mix(u_Color1, u_Color2, map(xlr, srx, erx, 0.0, 1.0));;
}
