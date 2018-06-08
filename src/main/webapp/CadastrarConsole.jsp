<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="PT-BR">
    <head>
        <meta charset="utf-8">
        <title>Jupiter - Cadastro de Consoles</title>
        <link rel="stylesheet" type="text/css" href="_css/estilo.css">
    </head>
    <body>
        <header>
            <a href="home.jsp"><p id="txtJupiter">JUPITER</p></a>
            <p id="usuario">Bem vindo Fulano de Tal</p>
        </header>
        <div id="menuContainer">
            <nav>
                <h2>Menu</h2>
                <ul id="menu">	
                    <a href="clientes.jsp"><li id="listaMenu"><img src="_imagens/cliente.png">Clientes</li></a>
                    <li id="listaSubMenu">
                        <img src="_imagens/produto.png">Produtos
                        <ul id="subMenuProdutos">
                            <a href="games.jsp"><li id="itemSub"><img src="_imagens/games.png">Games</li></a>
                            <a href="consoles.jsp"><li id="itemSub"><img src="_imagens/console.png">Consoles</li></a>
                            <a href="acessorios.jsp"><li id="itemSub"><img src="_imagens/acessorio.png">Acessórios</li></a>
                            <a href="actionFigure.jsp"><li id="itemSub"><img src="_imagens/actionFigure.png">Action Figures</li></a>
                        </ul>
                    </li>
                    <a href="usuarios.jsp"><li id="listaMenu"><img src="_imagens/funcionario.png">Usuários</li></a>
                    <a href="filiais.jsp"><li id="listaMenu"><img src="_imagens/filial.png">Filiais</li></a>
                    <a href="venda.jsp"><li id="listaMenu"><img src="_imagens/venda.png">Vendas</li></a>
                    <a href="relatorio.jsp"><li id="listaMenu"><img src="_imagens/relatorio.png">Relatório</li></a>
                </ul>
            </nav>		
        </div>
        <div id="corpo">
            <form action="${pageContext.request.contextPath}/CadastrarConsole" method="post" id="formularioCadastro">
                <input type="text" name="txtNome" placeholder="NOME" class="formulario">
                </br></br>
                <input type="text" name="txtDescricao" placeholder="DESCRIÇÃO" class="formulario">
                </br></br>
                <input type="text" name="txtFornecedor" placeholder="FORNECEDOR" class="formulario">
                </br></br>
                <input type="text" name="txtCor" placeholder="COR" class="formSelect">
                <input type="number" name="txtQuantidade" placeholder="QUANTIDADE" class="formSelect">
                </br></br>
                <input type="text" name="txtPrecoCompra" placeholder="PREÇO COMPRA" class="formSelect">
                <input type="text" name="txtPrecoVenda" placeholder="PREÇO VENDA" class="formSelect">
                </br></br>
                <input type="submit" value="SALVAR" id="botao">
            </form>
        </div>
    </body>
</html>