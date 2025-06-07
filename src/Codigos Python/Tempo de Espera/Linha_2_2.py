# Configurações: (hora_inicial, hora_final, intervalo_min)
configuracoes = [
    (365, 420, 9), # 06:05 até 07:00
    (420, 600, 8), # 07:00 até 10:00
    (600, 960, 5.5), # 10:00 até 16:00
    (960, 1320, 4), # 16:00 até 22:00
    (1320, 1380, 5.5), # 22:00 até 23:00
    (1380, 1440, 7), # 23:00 até 00:00
    (1440, 1500, 7), # 00:00 até 01:00
    (60, 120, 10), # 01:00 até 02:00
]

# 540

tempoPercuso = 112 + 106 + 42 + 93 + 60 + 65 + 73 - 540 + 51 + 68 + 111 + 79 + 63 + 93 + 93 - 540 + 210 + 164 + 213 - 540 + 162 + 129

print(tempoPercuso)

primeira = True
diferenca = 0
id = "par_4_325"

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