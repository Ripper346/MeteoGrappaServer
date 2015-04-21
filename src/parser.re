Ultimo aggiornamento([\s\S](?!ajaxdate))+[^>]+>(\d+)\/(\d+)\/(\d+)([\s\S](?!ajaxtime))+[^>]+>(\d\d:\d\d:\d\d)([\s\S](?!ajaxcurrentcond))+[^>]+>([^,]+), ([^<]+)([\s\S](?!ajaxtemp))+[^>]+>(\d+(\.\d+)?)([\s\S](?!ajaxhumidity))+[^>]+>(\d+)([\s\S](?!ajaxwind))+[^>]+>((\d+(\.\d+)?)|([^<]+))([\s\S](?!ajaxwinddir))+[^>]+>([^<]+)([\s\S](?!ajaxbaro))+[^>]+>(\d+(\.\d+)?)([\s\S](?!ajaxsolar))+[^>]+>(\d+)([\s\S](?! \d+%))+> (\d+)%([\s\S](?!ajaxuv))+[^>]+>(\d+(\.\d+)?)([\s\S](?!ajaxdew))+[^>]+>(\d+(\.\d+)?)([\s\S](?!ajaxrainratehr))+[^>]+>(\d+(\.\d+)?)([\s\S](?!ajaxapparenttemp))+[^>]+>(\d+(\.\d+)?)([\s\S](?!Altezza neve))+([\s\S](?!<span))+.[^>]+>(\d+(\.\d+)?)


data $4-$3-$2 $6
condizioni $8 $9
temperatura $11°C
umidità $14%
vento $16km/h
direzione vento $21
pressione $23hPa
radiazione solare $26W/m^2 $28%
uv $30
rugiada $33°C
pioggia $36mm/hr
percepita $39°C
neve $43cm