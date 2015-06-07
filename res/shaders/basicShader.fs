#version 330

in vec2 texCoord0;
out vec4 fragColor;

uniform sampler2D sampler;
uniform vec3 color;

void main(){
    vec4 textureColor = texture(sampler, texCoord0.xy);

       vec4 totalLight = vec4(ambientLight, 1);

       vec4 color = vec4(baseColor, 1);

       if(textureColor != vec4(0,0,0,1))
           color *= textureColor;

    fragColor = color * totalLight;
}