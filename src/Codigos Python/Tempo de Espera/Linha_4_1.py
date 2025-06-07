# Configurações: (hora_inicial, hora_final, intervalo_min)
import os

configuracoes = [
    (365, 420, 9), # 06:05 até 07:00
    (420, 660, 8), # 07:00 até 11:00
    (660, 1320, 6.5), # 11:00 até 22:00
    (1320, 1440, 7.5), # 22:00 até 24:00
    (0, 120, 12.5), # 00:00 até 02:00
]

# 540

tempoPercuso = 97 + 171 + 111 + 110 + 78 + 101 + 206 + 134 + 122 + 101 + 137 + 126 + 83 + 0 + 130 + 63 + 69 + 61 + 80 + 58 + 78 + 91 + 90

id = "par_4_266"

while tempoPercuso >= 540:
    tempoPercuso -= 540

print(tempoPercuso)

primeira = True
diferenca = 0

horas = 0

with open(f"{id}.txt", "w") as arquivo:

    for hora_min, hora_max, intervalo_min in configuracoes:


        hora_min += diferenca

        segundos_inicio = int(round(hora_min * 60))
        if(primeira):
            segundos_inicio += tempoPercuso

        segundos_fim = int(round(hora_max * 60))
        segundos_intervalo = int(round(intervalo_min * 60))

        if(primeira == False):
            if segundos_inicio + segundos_intervalo > segundos_fim:
                break
            segundos_inicio += segundos_intervalo

            if horas == segundos_inicio // 3600:
                if segundos_inicio + segundos_intervalo > segundos_fim:
                    break
                segundos_inicio += segundos_intervalo

        primeira = False

        # print(f"\nProcessando: {hora_min}min a {hora_max}min (intervalo: {intervalo_min}min)")

        
        while segundos_inicio < segundos_fim:
            # Converter segundos para horas, minutos e segundos
            total_segundos = segundos_inicio
            horas = total_segundos // 3600
            minutos = (total_segundos % 3600) // 60
            segundos = total_segundos % 60
            
            # Formatar a saída com segundos reais

            if(horas == 24):
                horas = 00

            arquivo.write(f"{id},{horas:02d}:{minutos:02d}:{segundos:02d},\n")

            #print(f"{id},{horas:02d}:{minutos:02d}:{segundos:02d},")
            
            # Avança o tempo
            if segundos_inicio + segundos_intervalo > segundos_fim:
                break
            segundos_inicio += segundos_intervalo

        # Calcula a diferença em minutos
        diferenca = (segundos_inicio - segundos_fim) / 60