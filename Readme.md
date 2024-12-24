# BottomSheet Compose Library

Uma biblioteca Jetpack Compose simples e personalizável para criar BottomSheets bonitos e funcionais em seus aplicativos Android.

[![](https://jitpack.io/v/evd-evanss/bottom-sheet-compose.svg)](https://jitpack.io/#evd-evanss/bottom-sheet-compose)

## Recursos

* Fácil de usar: basta adicionar o Composable `BottomSheet` ao seu layout.
* Personalizável: personalize a aparência do BottomSheet com parâmetros como `cornerSize`, `paddingValues`, `verticalArrangement` e `horizontalAlignment`.
* Expansível: controle o estado expandido/recolhido do BottomSheet com o parâmetro `isExpanded`.
* Função de fechamento: forneça uma função `onDismiss` para executar ações quando o BottomSheet for fechado.
* Suporte a conteúdo: adicione qualquer conteúdo Composable dentro do BottomSheet usando o parâmetro `content`.

## Instalação

Adicione a seguinte dependência ao seu arquivo `build.gradle.kts`:

```kotlin
dependencies { 
    implementation("com.github.evd-evanss:bottom-sheet-compose:latest-version" )
}
```

## Uso
```kotlin
@Composable
fun MyScreen() {
    var isExpanded by remember { mutableStateOf(false) }

    BottomSheet(
        isExpanded = isExpanded,
        onDismiss = { isExpanded = false },
        content = {
            Text("Olá, este é o conteúdo do BottomSheet!")
        }
    )

    Button(onClick = { isExpanded = true }) {
        Text("Abrir BottomSheet")
    }
}
```

## Personalização

Personalize a aparência do BottomSheet usando os seguintes parâmetros:

* `cornerSize`: define o raio dos cantos do BottomSheet.
* `paddingValues`: define o padding interno do BottomSheet.
* `verticalArrangement`: define o arranjo vertical do conteúdo do BottomSheet.
* `horizontalAlignment`: define o alinhamento horizontal do conteúdo do BottomSheet.

## Demonstração

![BottomSheet Demo](demo.gif)

## Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou pull requests.

## Licença

Esta biblioteca é licenciada sob a [Licença MIT](LICENSE).