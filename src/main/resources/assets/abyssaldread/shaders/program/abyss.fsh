#version 150

uniform sampler2D DiffuseSampler;
uniform vec2 OutSize;
uniform float GameTime;

in vec2 texCoord;
out vec4 fragColor;

float hash(vec2 p) {
    return fract(sin(dot(p, vec2(12.9898, 78.233))) * 43758.5453);
}

void main() {
    vec2 uv = texCoord;
    vec2 c = uv - 0.5;
    float r = length(c);

    // Houle : légère distorsion ondulante de l'image
    uv += vec2(sin(uv.y * 18.0 + GameTime * 1.7),
               cos(uv.x * 14.0 + GameTime * 1.3)) * 0.0028;

    // Respiration lente de l'abîme
    float pulse = 0.5 + 0.5 * sin(GameTime * 0.9);

    // Aberration chromatique qui grandit vers les bords
    vec2 dir = normalize(c + vec2(1e-4)) * r * 0.007 * (0.6 + 0.4 * pulse);
    vec3 col;
    col.r = texture(DiffuseSampler, uv + dir).r;
    col.g = texture(DiffuseSampler, uv).g;
    col.b = texture(DiffuseSampler, uv - dir).b;

    // Désaturation + teinte bleu-vert glaciale
    float lum = dot(col, vec3(0.299, 0.587, 0.114));
    col = mix(vec3(lum), col, 0.55);
    col *= vec3(0.62, 0.90, 1.00);
    col = mix(col, vec3(0.0, 0.05, 0.07), 0.18);

    // Contraste légèrement durci
    col = (col - 0.5) * 1.12 + 0.5;

    // Vignette pulsante : les bords se referment sur le joueur
    float vig = 1.0 - smoothstep(0.25, 0.85, r * (1.05 + 0.12 * pulse));
    col *= mix(0.22, 1.0, vig);

    // Grain de pellicule
    float g = hash(uv * OutSize + vec2(fract(GameTime) * 100.0)) - 0.5;
    col += g * 0.035;

    // Battement de coeur : assombrissement bref et périodique
    float beat = pow(max(0.0, sin(GameTime * 2.2)), 16.0);
    col *= 1.0 - beat * 0.12;

    fragColor = vec4(clamp(col, 0.0, 1.0), 1.0);
}
