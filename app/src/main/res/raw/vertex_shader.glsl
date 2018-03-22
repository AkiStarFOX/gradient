
attribute vec4 a_Position;
attribute vec2 a_TexCoord;
uniform mat4 u_ProjM;
uniform mat4 u_ModelM;

varying vec2 v_TexCoord;

void main()
{
    mat4 mvp = u_ProjM * u_ModelM;
    gl_Position = mvp * a_Position;
    v_TexCoord = a_TexCoord;
}