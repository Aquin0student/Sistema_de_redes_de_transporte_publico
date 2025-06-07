# Configurações: (hora_inicial, hora_final, intervalo_min)
configuracoes = [
    (365, 420, 9),    # 06:05 até 07:00
    (420, 780, 7),   # 07:00 até 09:00
    (780, 1080, 6),
    (1080, 1320, 5),   # 09:00 até ~12:05 (exemplo onde 20min não divide exato)
    (1320, 1380, 7.5),
    (1380, 1500, 7.5),
    (60, 120, 10.5),
]

# 540

tempoPercuso = 102 + 215 + 107 + 125 - 540 + 80 + 209 + 99 + 103 + 89 - 540 + 90 + 74 + 112 + 84 + 111 + 49 - 540 + 107 + 76 + 107 + 51 + 109 + 75 - 540 + 111 + 116 + 81 + 69
tempoPercuso += 84 + 120 - 540 + 88 + 81 + 182 + 126 + 190 - 540

print(tempoPercuso)

primeira = True
diferenca = 0
id = "par_4_263"

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