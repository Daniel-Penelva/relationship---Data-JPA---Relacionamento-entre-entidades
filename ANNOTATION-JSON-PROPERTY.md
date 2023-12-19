# Anotação `@JsonProperty`

A anotação `@JsonProperty` é uma anotação fornecida pela biblioteca Jackson, que é amplamente usada para processamento de JSON em Java. Essa anotação é usada para mapear um campo em sua classe Java para uma propriedade específica quando essa classe é serializada (escrita) em JSON ou desserializada (lida) de JSON.

---
**OBS.** A serialização refere-se ao processo de converter um objeto em uma representação de dados que pode ser facilmente armazenada ou transmitida, como JSON, XML ou bytes. Essa representação serializada pode então ser gravada em um arquivo, enviada pela rede ou de alguma forma persistida.

Por outro lado, a desserialização é o processo inverso, em que a representação serializada dos dados é convertida de volta em um objeto. Isso é comumente usado quando recebe dados de uma fonte externa, como uma solicitação HTTP que contém dados JSON, e precisa converter esses dados de volta para objetos Java.

Ambos os processos são fundamentais para a comunicação eficiente entre sistemas, especialmente em ambientes distribuídos onde os dados precisam ser transmitidos pela rede ou armazenados de maneira persistente em um formato que possa ser facilmente recuperado e reconstruído.
---

No meu exemplo, a anotação `@JsonProperty("zipcode_id")` está sendo usada para indicar que o campo Java chamado `zipcodeId` deve ser mapeado para a propriedade chamada `"zipcode_id"` quando a classe é convertida para JSON.

Considere o meu exemplo AuthorRequestDto:

```java
public class AuthorRequestDto {
    @JsonProperty("zipcode_id")
    private Long zipcodeId;

    // outros campos e métodos
}
```

Quando converto uma instância dessa classe para JSON usando um processador Jackson (por exemplo, ao enviar dados JSON em uma solicitação HTTP ou ao gravar em um arquivo JSON), o campo `zipcodeId` será representado como `"zipcode_id"` no JSON resultante.

Por exemplo, se tiver uma instância `AuthorRequestDto` com `zipcodeId` igual a `1`, o JSON resultante seria algo assim:

```json
{
    "zipcode_id": 1
    // outros campos, se houver
}
```

Essa anotação é útil quando precisa mapear nomes de propriedades em seus objetos Java para nomes diferentes em seus documentos JSON. Pode ser especialmente útil quando precisa se integrar com APIs ou serviços que esperam uma estrutura JSON específica.

## Detalhe

Para a desserialização, a biblioteca Jackson usa a anotação `@JsonProperty` também, mas é configurada de maneira um pouco diferente. Quando deseja mapear um nome de propriedade JSON para um nome de campo Java durante a desserialização, você pode usar a anotação `@JsonProperty` diretamente no campo.

Por exemplo, suponha que tenha a seguinte classe Java para representar dados JSON:

```java
public class AuthorRequestDto {
    @JsonProperty("zipcode_id")
    private Long zipcodeId;

    // outros campos e métodos
}
```

Ao desserializar um JSON que contém a propriedade `"zipcode_id"`, a anotação `@JsonProperty` é usada para associar esse nome de propriedade ao campo `zipcodeId` durante o processo de desserialização.

A biblioteca Jackson cuida automaticamente do processo de desserialização, usando as anotações `@JsonProperty` presentes nos campos da classe para mapear os nomes das propriedades JSON para os nomes dos campos Java correspondentes.

Portanto, tanto para a serialização quanto para a desserialização, a anotação `@JsonProperty` é usada, mas sua aplicação é contextual. Quando está presente em campos, ela influencia a desserialização, e quando está presente em métodos ou campos, ela influencia a serialização.

## No meu caso estou discutindo a desserialização

A anotação `@JsonProperty("zipcode_id")` estava presente no campo `zipcodeId` da classe `AuthorRequestDto`. Essa anotação informa ao Jackson durante o processo de desserialização que, ao encontrar a propriedade JSON `"zipcode_id"`, ela deve ser associada ao campo `zipcodeId` na classe Java `AuthorRequestDto`. Isso é feito automaticamente pela biblioteca Jackson quando ela desserializa um objeto JSON em uma instância da classe `AuthorRequestDto`.